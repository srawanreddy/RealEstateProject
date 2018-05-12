package com.example.sravanreddy.realestateproject.data.remote

import android.util.Log
import com.example.sravanreddy.realestateproject.common.Constants.Companion.CITY_TYPE
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.models.boundarypojo.BoundaryResponse
import com.example.sravanreddy.realestateproject.models.coordinatepojo.CoordResponse
import com.example.sravanreddy.realestateproject.models.coordinatepojo.Item
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
    var boundItems: List<Item>? = null
    var areaId: String? = null
    private lateinit var propertyModelList: MutableList<PropertyModel>

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
        propertyModelList = mutableListOf<PropertyModel>()
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
                        netCallback.onSuccess(propertyModelList)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(propertyModels: PropertyModel) {
                        propertyModelList.add(propertyModels)
                    }

                    override fun onError(e: Throwable) {
                        netCallback.onFailure(e)
                    }

                })
    }

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack) {
        var retrofit = RetrofitHelper.getOnBoardApi()
        Log.d("Calling", "klajsdkfj")
        retrofit.getArea(latLng.latitude, latLng.longitude)
                .flatMap(object : Function<BoundaryResponse, Observable<CoordResponse>> {
                    //TODO Convert to Lambda expression
                    override fun apply(bresponse: BoundaryResponse): Observable<CoordResponse> {
                        var items: List<com.example.sravanreddy.realestateproject.models.boundarypojo.Item> = bresponse.response.result.resultpackage.item
                        var iter = items.listIterator()

                        while (iter.hasNext()) {
                            val item = iter.next()
                            if (item.type == CITY_TYPE && item.name.contains(cityName)) {
                                areaId = item.id
                                break
                            }
                        }

                        return RetrofitHelper.getOnBoardApi().getBound(areaId!!)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CoordResponse> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("onSubscribe", "onSubscribeMsg")
                    }

                    override fun onNext(coordResponse: CoordResponse) {
                        Log.d("onNext", "onNext0900")
                        netCallback.onSuccess(coordResponse, areaId!!)
                    }

                    override fun onError(e: Throwable) {
                        //Log.d("onError", e.message)
                        netCallback.onFailure(e)
                    }

                    override fun onComplete() {
                        Log.d("onComplete", "Completed Request")
                    }
                }

                )
    }
}