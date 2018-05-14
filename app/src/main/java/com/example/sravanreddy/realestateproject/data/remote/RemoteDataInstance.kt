package com.example.sravanreddy.realestateproject.data.remote

import com.example.sravanreddy.realestateproject.common.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataInstance {



        public fun getRetrofitInstance() : Retrofit{

            var retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASEURL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()


            return retrofit
        }


}