package com.example.sravanreddy.realestateproject.view.activity

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.view.fragment.MapViewFragment
import com.example.sravanreddy.realestateproject.view.fragment.PropertyListFragment
import kotlinx.android.synthetic.main.activity_buyer.view.*

class BuyerActivity : AppCompatActivity() {
    private lateinit var mapviewtv : TextView
    private lateinit var listViewtv : TextView
    private lateinit var sorttv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)
        mapviewtv = findViewById(R.id.tv_mapView_buyer)
        listViewtv = findViewById(R.id.tv_listView_buyer)
        sorttv = findViewById(R.id.tv_sort_buyer)
        mapviewtv!!.setOnClickListener(object :View.OnClickListener {
            override fun onClick(p0: View?) {
                sorttv.setVisibility(View.INVISIBLE)

                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, MapViewFragment()).commit()
            }
        })
        listViewtv!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                sorttv.setVisibility(View.VISIBLE)
              var propertyListFragment = PropertyListFragment()
                var calledFrom = Bundle()
                calledFrom.putInt("Called From", 1)
                propertyListFragment.arguments = calledFrom
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, propertyListFragment).commit()
            }
        })
        var propertyListFragment = PropertyListFragment()
        var calledFrom = Bundle()
        calledFrom.putInt("Called From", 1)
        propertyListFragment.arguments = calledFrom
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_results, propertyListFragment).commit()
    }
}
