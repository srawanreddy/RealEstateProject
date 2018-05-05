package com.example.sravanreddy.realestateproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.sravanreddy.realestateproject.utils.adapters.ViewPagerAdapter

class LoginActivity : AppCompatActivity() {
    private var  viewPager : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewPager = findViewById(R.id.vp_imageslider_login)
       this.viewPager!!.adapter = ViewPagerAdapter(this)
    }
}
