package com.example.sravanreddy.realestateproject.data

import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.google.android.gms.maps.model.LatLng

class DataManager private constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : IDataSource {


    var netDataSource: RemoteDataSource = remoteDataSource
    var dbDataSource: LocalDataSource = localDataSource

    companion object {
        @Volatile
        var instance: DataManager? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): DataManager {
            if (instance == null) {
                synchronized(DataManager::class) {
                    if (instance == null) {
                        instance = DataManager(remoteDataSource, localDataSource)
                    }
                }
            }
            return instance!!
        }
    }

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack) {
        netDataSource.getAreaData(cityName, latLng, netCallback)
    }

    override fun getProperties(netCallback: IDataSource.NetworkCallBack, searchText: String) {
        netDataSource.getProperties(netCallback, searchText)
    }

    override fun getPropertyInArea(latitude: Double, longitude: Double, networkCallBack: IDataSource.NetworkCallBack) {
        netDataSource.getPropertyInArea(latitude, longitude, networkCallBack)
    }

}