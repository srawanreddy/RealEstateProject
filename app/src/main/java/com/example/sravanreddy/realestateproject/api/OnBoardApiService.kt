package com.example.sravanreddy.realestateproject.api

import com.example.sravanreddy.realestateproject.models.pojoArea.BoundaryResponse
import com.example.sravanreddy.realestateproject.models.pojoBoundCoodinates.CoordResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OnBoardApiService {

    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("hierarchy/lookup")
    fun getArea(@Query("latitude") latitude: Double,
                @Query("longitude") longitude: Double): io.reactivex.Observable<BoundaryResponse>

    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("boundary/detail")
    fun getBound(@Query("AreaId") areaId: String): io.reactivex.Observable<CoordResponse>
}