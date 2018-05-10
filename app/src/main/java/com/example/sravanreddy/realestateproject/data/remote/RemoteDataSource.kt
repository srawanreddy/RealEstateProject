package com.example.sravanreddy.realestateproject.data.remote

import android.util.Log
import com.example.sravanreddy.realestateproject.api.OnBoardRetrofitInstance
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.boundarypojo.BoundaryResponse
import com.example.sravanreddy.realestateproject.models.boundarypojo.Item
import com.example.sravanreddy.realestateproject.models.coordinatepojo.CoordResponse
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class RemoteDataSource : IDataSource {
    private val CITY_TYPE = "PL"
    var boundItems: List<com.example.sravanreddy.realestateproject.models.coordinatepojo.Item>? = null
    var areaId: String? = null
    override fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack) {
        var retrofit = OnBoardRetrofitInstance.getOnBoardRetrofitIns()
        retrofit.getArea(latLng.latitude, latLng.longitude)
                .flatMap(object : Function<BoundaryResponse, Observable<CoordResponse>> {
                    //TODO Convert to Lambda expression
                    override fun apply(bresponse: BoundaryResponse): Observable<CoordResponse> {
                        var items: List<Item> = bresponse.response.result.resultpackage.item
                        var iter = items.listIterator()

                        while (iter.hasNext()) {
                            val item = iter.next()
                            if (item.type == CITY_TYPE && item.name.contains(cityName)) {
                                areaId = item.id
                                break
                            }
                        }

                        return OnBoardRetrofitInstance.getOnBoardRetrofitIns().getBound(areaId!!)
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