package com.example.sravanreddy.realestateproject.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sravanreddy.realestateproject.utils.dagger.AppApplication
import com.example.sravanreddy.realestateproject.utils.dagger.AppComponent

/**
 * @Package com.example.sravanreddy.realestateproject.base
 * @FileName BaseActivity
 * @Date 5/10/18, 11:18 AM
 * @Author Created by fengchengding
 * @Description RealEstateProject
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityComponent(AppApplication.get(this).getAppComponent())
    }

    protected abstract fun setupActivityComponent(appComponent: AppComponent)
}