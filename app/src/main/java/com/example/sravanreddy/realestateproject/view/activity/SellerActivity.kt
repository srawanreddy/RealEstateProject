package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.view.fragment.BoundaryFragment
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import kotlinx.android.synthetic.main.activity_seller.*

class SellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, PropertyListFragment()).commit()
        tv_mapView_seller.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentSellerContainer, BoundaryFragment()).commit()
        }

    }
}
