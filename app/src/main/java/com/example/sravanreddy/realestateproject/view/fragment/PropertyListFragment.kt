package com.example.sravanreddy.realestateproject.view.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.adapters.PropertyAdapter
import com.example.sravanreddy.realestateproject.models.PropertyModel

class PropertyListFragment : Fragment(), OnClickListener, PropertyListContract.IView {

    private lateinit var fabMore: FloatingActionButton
    private lateinit var fabWish: FloatingActionButton
    private lateinit var fabWatch: FloatingActionButton
    private lateinit var fabZoom: Animation
    private lateinit var fabZoomOut: Animation
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    lateinit var recyclerView: RecyclerView
    private lateinit var presenter: PropertyListContract.IPresenter
    private var isOpen = false
    private var calledFrom: Int? = null



    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab_more_property -> {
                if (isOpen) {
                    fabWatch.setVisibility(INVISIBLE)
                    fabWish.setVisibility(INVISIBLE)
                    fabMore.startAnimation(fabZoomOut)
                    fabWish.startAnimation(fabClose)
                    fabWatch.startAnimation(fabClose)
                    isOpen = false
                } else {
                    fabWatch.setVisibility(VISIBLE)
                    fabWish.setVisibility(VISIBLE)
                    fabMore.startAnimation(fabZoom)
                    fabWish.startAnimation(fabOpen)
                    fabWatch.startAnimation(fabOpen)
                    isOpen = true
                }
            }

            R.id.property_image -> {
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
        calledFrom = arguments!!.getInt("Called From")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView: View
        if (calledFrom == 0) {
            Log.d("SellerProperty", "Created PropertyList for Seller")
            mView = inflater.inflate(R.layout.fragment_property_list_seller, container, false)
            recyclerView = mView.findViewById(R.id.propertyList_recycler_seller)
        } else {
            Log.d("BuygerProperty", "Created PropertyList for Buyer")
            mView = inflater.inflate(R.layout.fragment_property_list, container, false)
            recyclerView = mView.findViewById(R.id.propertyList_recycler)
            fabMore = mView.findViewById(R.id.fab_more_property)
            fabWish = mView.findViewById<FloatingActionButton>(R.id.fab_wishlist_property)
            fabWatch = mView.findViewById<FloatingActionButton>(R.id.fab_watchList_property)
            fabZoom = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_zoom)
            fabZoomOut = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_zoom_out)
            fabOpen = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_open)
            fabClose = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_close)
            fabMore.setOnClickListener(this::onClick)
        }
        return mView
    }


    override fun setRecylcerView(propertyModels: ArrayList<PropertyModel>) {
        Log.d("propertyModeSize", propertyModels.size.toString())
        val propertyAdapter = PropertyAdapter(propertyModels, this, context!!, calledFrom!!)
        recyclerView.adapter = propertyAdapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        propertyAdapter.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: PropertyListContract.IPresenter) {
        this.presenter = presenter
    }

}