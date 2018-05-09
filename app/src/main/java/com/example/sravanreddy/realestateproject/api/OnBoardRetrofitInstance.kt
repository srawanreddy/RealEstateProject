package com.example.sravanreddy.realestateproject.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OnBoardRetrofitInstance {
    private val BASE_URL = "https://search.onboard-apis.com/areaapi/v2.0.0/"
    private var retrofit: Retrofit? = null
    val onBoardRetrofitInstance: Retrofit
        get() {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
}