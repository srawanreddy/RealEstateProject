package com.example.sravanreddy.realestateproject.utils.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val mApp: AppApplication) {

    //private val mApplication: AppApplication = mApp

//    @Provides
//    @Singleton
//    fun provideApplication(): Application {
//        return mApplication
//    }

    @Provides
    @Singleton
    fun provideApplication() = mApp
}