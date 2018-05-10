package com.example.sravanreddy.realestateproject.utils.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(mApp: AppApplication) {

    private val mApplication: AppApplication = mApp

    @Provides
    @Singleton
    protected fun provideApplication(): Application {
        return mApplication
    }

}