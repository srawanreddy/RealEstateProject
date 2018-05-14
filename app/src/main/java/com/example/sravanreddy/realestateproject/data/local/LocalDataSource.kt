package com.example.sravanreddy.realestateproject.data.local

import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.google.android.gms.maps.model.LatLng

/**
 * @Package com.example.sravanreddy.realestateproject.data
 * @FileName LocalDataSource
 * @Date 5/10/18, 10:11 AM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */
class LocalDataSource: IDataSource {
    override fun getPropertyInArea(latitude: Double, longitude: Double, networkCallBack: IDataSource.NetworkCallBack) {

    }

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getLocalInstance(): LocalDataSource {
            if (instance == null) {
                synchronized(RemoteDataSource::class) {
                    if (instance == null) {
                        instance = LocalDataSource()
                    }
                }
            }
            return instance!!
        }
    }
    override fun getProperties(netCallback: IDataSource.NetworkCallBack, searchText: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAreaData(cityName: String, latLng: LatLng, netCallback: IDataSource.NetworkCallBack) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}