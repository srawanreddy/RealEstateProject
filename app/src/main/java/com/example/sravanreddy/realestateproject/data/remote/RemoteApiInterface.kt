package com.example.sravanreddy.realestateproject.data.remote

import android.provider.SyncStateContract
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.models.PropertyModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiInterface {
    @GET(Constants.ENDURL)
    abstract fun getProperties():Observable<List<PropertyModel>>

}