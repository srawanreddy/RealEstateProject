package com.example.sravanreddy.realestateproject.data

import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.IRemoteDataHelper
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataHelper
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.google.android.gms.maps.model.LatLng

class DataManager : IDataManager, IDataSource{

     var iRemoteDataHelper : IRemoteDataHelper = RemoteDataHelper()


    override fun getProperties(iRemoteDataHelperListener: IRemoteDataHelper.IRemoteDataHelperListener, searchText : String) {
    iRemoteDataHelper.getProperties(iRemoteDataHelperListener, searchText)
    }

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback:IDataSource.NetworkCallBack) {
        remoteDataSource.getAreaData(cityName, latLng, netCallback)
    }

    private  var localDataSource: LocalDataSource = LocalDataSource()
    private var remoteDataSource: RemoteDataSource = RemoteDataSource()
}