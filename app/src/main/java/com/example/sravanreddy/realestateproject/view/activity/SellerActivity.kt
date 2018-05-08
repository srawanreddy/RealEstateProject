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
        setContentView(R.layout.activity_property)
        var propertyListFragment = PropertyListFragment()
        var calledFrom = Bundle()
        calledFrom.putInt("Called From", 0)
        propertyListFragment.arguments = calledFrom
        supportFragmentManager.beginTransaction().replace(R.id.PropertyFragmentContainer, propertyListFragment).commit()
    }
}
