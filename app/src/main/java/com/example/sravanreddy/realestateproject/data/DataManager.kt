package com.example.sravanreddy.realestateproject.data

import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.google.android.gms.maps.model.LatLng

class DataManager() : IDataSource{

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback:IDataSource.NetworkCallBack) {
        remoteDataSource.getAreaData(cityName, latLng, netCallback)
    }

    private  var localDataSource:LocalDataSource = LocalDataSource()
    private var remoteDataSource:RemoteDataSource = RemoteDataSource()
}