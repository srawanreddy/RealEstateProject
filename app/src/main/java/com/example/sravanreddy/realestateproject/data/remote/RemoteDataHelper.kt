package com.example.sravanreddy.realestateproject.data.remote

import android.util.Log
import com.example.sravanreddy.realestateproject.base.DaggerComponent
import com.example.sravanreddy.realestateproject.base.DaggerDaggerComponent
import com.example.sravanreddy.realestateproject.base.DaggerModule
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.models.PropertyModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.functions.Predicate
import javax.inject.Inject

class RemoteDataHelper : IRemoteDataHelper{

private lateinit var iRemoteDataHelperListener: IRemoteDataHelper.IRemoteDataHelperListener;
    private lateinit var retrofit : Retrofit
    @Inject
    lateinit var remoteDataInstance: RemoteDataInstance
    override fun getProperties(iRemoteDataHelperListener: IRemoteDataHelper.IRemoteDataHelperListener, searchText : String) {
        this.iRemoteDataHelperListener = iRemoteDataHelperListener
        val daggerComponent: DaggerComponent by lazy {
            DaggerDaggerComponent
                    .builder()
                    .daggerModule(DaggerModule())
                    .build()}
        daggerComponent.inject(this)
        retrofit = remoteDataInstance!!.getRetrofitInstance()

//        retrofit = Retrofit.Builder()
//                .baseUrl(Constants.BASEURL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
        retrofit.create(RemoteApiInterface::class.java)
                .getProperties()
                .subscribeOn(Schedulers.io())
                .flatMap(object : Function< List<PropertyModel>,  ObservableSource<PropertyModel>>{
                    override fun apply(t: List<PropertyModel>): ObservableSource<PropertyModel> {
                        return Observable.fromIterable(t)
                    }

                }).filter(object : Predicate<PropertyModel>{
                    override fun test(t: PropertyModel): Boolean {
                        var address = t.getPropertyAddress1()+t.getPropertyAddress2()+t.getPropertyZip()
                        return address.toLowerCase().contains(searchText)
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError);
    }

      fun handleError(throwable: Throwable) {
        iRemoteDataHelperListener.onFailure(throwable.message+"")
    }
        fun handleResponse(propertyModels: PropertyModel) {
        iRemoteDataHelperListener.onSuccess(propertyModels)
    }



}

