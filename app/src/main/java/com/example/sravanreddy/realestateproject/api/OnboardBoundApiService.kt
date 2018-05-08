package com.example.sravanreddy.realestateproject.api

import com.example.sravanreddy.realestateproject.models.Area
import com.example.sravanreddy.realestateproject.models.BoundaryDetail
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface OnboardBoundApiService {
    @Headers(
        "Accept:application/json",
        "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("hierarchy/lookup")
    fun getAreaId(@Query("latitude") latitude: String,
                  @Query("longitude") longitude: String) : io.reactivex.Observable<Area>

    @Headers(
            "Accept:application/json",
            "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("boundary/detail")
    fun getBoundaryPoints(@Query("AreaId") areaId: String) : io.reactivex.Observable<BoundaryDetail>
}