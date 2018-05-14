package com.example.sravanreddy.realestateproject.base

import android.app.Activity
import com.example.sravanreddy.realestateproject.view.activity.BuyerActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(DaggerModule::class))
@Singleton
public interface DaggerComponent {
    //public fun inject(remoteDataHelper: RemoteDataHelper)
}