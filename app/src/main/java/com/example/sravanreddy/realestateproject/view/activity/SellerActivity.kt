package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment

class SellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        Log.d("SellerActivity", "Adding PropertyList Fragment")
        supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, PropertyListFragment()).commit()
    }
}
