package com.example.sravanreddy.realestateproject.data.remote

import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.network.RetrofitHelper
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

/**
 * @Package com.example.sravanreddy.realestateproject.data
 * @FileName RemoteDataSource
 * @Date 5/10/18, 10:11 AM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */
class RemoteDataSource : IDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getRemoteInstance(): RemoteDataSource {
            if (instance == null) {
                synchronized(RemoteDataSource::class) {
                    if (instance == null) {
                        instance = RemoteDataSource()
                    }
                }
            }
            return instance!!
        }
    }


    override fun getProperties(netCallback: IDataSource.NetworkCallBack, searchText: String) {

        RetrofitHelper.getAmirApi().getProperties()
                .subscribeOn(Schedulers.io())
                .flatMap(object : Function<List<PropertyModel>, ObservableSource<PropertyModel>> {
                    override fun apply(t: List<PropertyModel>): ObservableSource<PropertyModel> {
                        return Observable.fromIterable(t)
                    }

                }).filter(object : Predicate<PropertyModel> {
                    override fun test(t: PropertyModel): Boolean {
                        val address = t.getPropertyAddress1() + t.getPropertyAddress2() + t.getPropertyZip()
                        return address.toLowerCase().contains(searchText)
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PropertyModel> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(propertyModels: PropertyModel) {
                        netCallback.onSuccess(propertyModels)
                    }

                    override fun onError(e: Throwable) {
                        netCallback.onFailure(e)
                    }

                })
    }

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack) {
    }
}