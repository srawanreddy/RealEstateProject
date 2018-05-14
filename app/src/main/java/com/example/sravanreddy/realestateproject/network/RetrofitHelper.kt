package com.example.sravanreddy.realestateproject.network

import com.example.sravanreddy.realestateproject.common.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Package com.example.sravanreddy.realestateproject.network
 * @FileName RetrofitHelper
 * @Date 5/10/18, 11:35 AM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */
object RetrofitHelper {
    private lateinit var retrofit: Retrofit

    fun getAmirApi(): AmirApi {
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(AmirApi::class.java)
    }

    fun getOnBoardApi(): OnBoardApi{
        retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(Constants.BOARD_BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(OnBoardApi::class.java)
    }


}