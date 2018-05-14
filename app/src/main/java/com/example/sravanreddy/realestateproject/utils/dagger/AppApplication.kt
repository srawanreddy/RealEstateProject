package com.example.sravanreddy.realestateproject.utils.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.sravanreddy.realestateproject.data.local.PropertyDataBase

class AppApplication : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        fun get(context: Context): AppApplication {
            return context.applicationContext as AppApplication
        }

        //var propertyDatabase: PropertyDataBase?=null

    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                //list of modules that are part of this component need to be created here too
                .appModule(AppModule(this))
                .mvpModule(MvpModule())
                .build()
        //AppApplication.propertyDatabase = Room.databaseBuilder(this, PropertyDataBase::class.java, "propertyDb").build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }


}






