package com.example.sravanreddy.realestateproject.data

import com.google.android.gms.maps.model.LatLng

interface IDataSource {
    fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack)

    interface LocalCallBack {
        fun onSuccess(response: Any)
        fun onFailure(msg: String)
    }

    interface NetworkCallBack {
        fun onSubscribe()
        fun onSuccess(response: Any)
        fun onSuccess(response: Any, areaId: String)
        fun onFailure(t: Throwable)
    }

    fun getProperties(netCallback: NetworkCallBack, searchText: String)
}