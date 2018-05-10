package com.example.sravanreddy.realestateproject.utils.dagger

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application):
    // Application reference must come from AppModule.class
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }


    @Provides
    @Singleton
    internal fun providesDataManager(): DataManager {
        return DataManager.getInstance(RemoteDataSource.getRemoteInstance(), LocalDataSource.getLocalInstance())
    }
}