package com.example.sravanreddy.realestateproject.network

import com.example.sravanreddy.realestateproject.models.boundarypojo.BoundaryResponse
import com.example.sravanreddy.realestateproject.models.coordinatepojo.CoordResponse
import com.example.sravanreddy.realestateproject.models.propertybean.PropertyRes
import io.reactivex.Observable
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
    @GET("areaapi/v2.0.0/hierarchy/lookup")
    fun getArea(@Query("latitude") latitude: Double,
                @Query("longitude") longitude: Double): io.reactivex.Observable<BoundaryResponse>

    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("areaapi/v2.0.0/boundary/detail")
    fun getBound(@Query("AreaId") areaId: String): io.reactivex.Observable<CoordResponse>

    @Headers("Accept:application/json", "APIKey:57191c1300a60167f2ed1bef50c5f9f4")
    @GET("propertyapi/v1.0.0/property/snapshot")
    fun getPropertyInArea(@Query("latitude") latitude: Double,
                          @Query("longitude") longitude: Double,
                          @Query("radius") radius: Int,
                          @Query("mintaxamt") mintaxamt: Double,
                          @Query("maxtaxamt") maxtaxamt: Double): Observable<PropertyRes>
}