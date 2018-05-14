package com.example.sravanreddy.realestateproject.network

import android.provider.SyncStateContract
import com.example.sravanreddy.realestateproject.common.Constants
import com.example.sravanreddy.realestateproject.models.PropertyModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AmirApi {
    @GET(Constants.ENDURL)
    fun getProperties():Observable<List<PropertyModel>>
}