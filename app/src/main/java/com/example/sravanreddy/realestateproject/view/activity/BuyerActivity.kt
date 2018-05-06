package com.example.sravanreddy.realestateproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import kotlinx.android.synthetic.main.activity_buyer.view.*

class BuyerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, PropertyListFragment()).commit()
    }
}
