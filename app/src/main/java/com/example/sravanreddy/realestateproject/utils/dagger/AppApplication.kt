package com.example.sravanreddy.realestateproject.utils.dagger

import android.app.Application
import android.content.Context

class AppApplication : Application() {
    private lateinit var appComponent: AppComponent

    companion object {
        fun get(context: Context): AppApplication {
            return context.applicationContext as AppApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                //list of modules that are part of this component need to be created here too
                .appModule(AppModule(this))
                .mvpModule(MvpModule())
                .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }


}






