package com.example.sravanreddy.realestateproject.network

import com.example.sravanreddy.realestateproject.models.boundarypojo.BoundaryResponse
import com.example.sravanreddy.realestateproject.models.coordinatepojo.CoordResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @Package com.example.sravanreddy.realestateproject.network
 * @FileName OnBoardApi
 * @Date 5/10/18, 11:39 AM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */
interface OnBoardApi {
    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("hierarchy/lookup")
    fun getArea(@Query("latitude") latitude: Double,
                @Query("longitude") longitude: Double): io.reactivex.Observable<BoundaryResponse>

    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("boundary/detail")
    fun getBound(@Query("AreaId") areaId: String): io.reactivex.Observable<CoordResponse>
}