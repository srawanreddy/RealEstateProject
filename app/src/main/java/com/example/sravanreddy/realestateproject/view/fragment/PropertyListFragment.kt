package com.example.sravanreddy.realestateproject.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.adapters.PropertyAdapter
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.example.sravanreddy.realestateproject.view.activity.AddPropertyActivity
import com.example.sravanreddy.realestateproject.view.activity.FavouritesList
import com.example.sravanreddy.realestateproject.view.activity.WatchList
import kotlinx.android.synthetic.main.fragment_property_list_seller.*

class PropertyListFragment : Fragment(), OnClickListener, PropertyListContract.IView {

    private lateinit var iPresenter: PropertyListContract.IPresenter

    override fun setRecylcerView(propertyModels: ArrayList<PropertyModel>) {

        val propertyAdapter = PropertyAdapter(propertyModels, this, context!!, calledFrom!!)

        recyclerView.adapter = propertyAdapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        propertyAdapter.notifyDataSetChanged()
    }


    override fun setPresenter(presenter: PropertyListContract.IPresenter) {
        iPresenter = presenter
    }

    private lateinit var fabMore: FloatingActionButton
    private lateinit var fabWish: FloatingActionButton
    private lateinit var fabWatch: FloatingActionButton
    private lateinit var fabZoom: Animation
    private lateinit var fabZoomOut: Animation
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var recyclerView: RecyclerView
    private lateinit var propertyModels: ArrayList<PropertyModel>
    private var isOpen = false
    private var calledFrom: Int? = null
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab_more_property -> {
                if (isOpen) {
                    fabWatch.visibility = INVISIBLE
                    fabWish.visibility = INVISIBLE
                    fabMore.startAnimation(fabZoomOut)
                    fabWish.startAnimation(fabClose)
                    fabWatch.startAnimation(fabClose)
                    isOpen = false
                } else {
                    fabWatch.visibility = VISIBLE
                    fabWish.visibility = VISIBLE
                    fabMore.startAnimation(fabZoom)
                    fabWish.startAnimation(fabOpen)
                    fabWatch.startAnimation(fabOpen)
                    isOpen = true
                }
            }

            R.id.fab_wishlist_property ->{
                startActivity(Intent(activity!!.baseContext, FavouritesList::class.java))
            }
            R.id.fab_watchList_property ->{
                startActivity(Intent(activity!!.baseContext, WatchList::class.java))
            }

            R.id.property_image -> {

            }
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calledFrom = arguments!!.getInt("Called From")
        if (calledFrom == 0) {
            iPresenter.start()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView: View
        if (calledFrom == 0) {
            mView = inflater.inflate(R.layout.fragment_property_list_seller, container, false)
            recyclerView = mView.findViewById(R.id.propertyList_recycler_seller)
            val addProperty: FloatingActionButton = mView.findViewById(R.id.addProperty)
            addProperty.setOnClickListener{
                val intent = Intent(activity, AddPropertyActivity::class.java)
                startActivity(intent)
            }
        } else {
            propertyModels = arguments!!.getParcelableArrayList("Property Model")
            mView = inflater.inflate(R.layout.fragment_property_list, container, false)
            recyclerView = mView.findViewById(R.id.propertyList_recycler)
            fabMore = mView.findViewById(R.id.fab_more_property)

            fabWish = mView.findViewById(R.id.fab_wishlist_property)
            fabWish.setOnClickListener(this::onClick)
            fabWatch = mView.findViewById(R.id.fab_watchList_property)
            fabWatch.setOnClickListener(this::onClick)

            fabZoom = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_zoom)
            fabZoomOut = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_zoom_out)
            fabOpen = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_open)
            fabClose = AnimationUtils.loadAnimation(activity!!.applicationContext, R.anim.fab_close)
            fabMore.setOnClickListener(this::onClick)
            setRecylcerView(propertyModels)

        }


        return mView
    }

    override fun onStart() {
        super.onStart()
       if(calledFrom == 1) setRecylcerView(propertyModels)
    }

}