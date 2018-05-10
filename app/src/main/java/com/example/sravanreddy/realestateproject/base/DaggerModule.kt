package com.example.sravanreddy.realestateproject.base

import com.example.sravanreddy.realestateproject.data.remote.RemoteDataInstance
import dagger.Module
import dagger.Provides

@Module
public class DaggerModule {
    @Provides
            public fun getRetrofitInstanceInjected() : RemoteDataInstance{
                return RemoteDataInstance()
            }
}