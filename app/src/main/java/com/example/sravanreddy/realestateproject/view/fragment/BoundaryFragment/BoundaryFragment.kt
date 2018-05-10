package com.example.sravanreddy.realestateproject.view.fragment.BoundaryFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.local.LocalDataSource
import com.example.sravanreddy.realestateproject.data.remote.RemoteDataSource
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Polygon

class BoundaryFragment : Fragment(), View.OnClickListener, OnMapReadyCallback, GoogleMap.OnPolygonClickListener, BoundaryContract.IView{
    private lateinit var cityName:String
    private lateinit var presenter: BoundaryContract.IPresenter
    override fun onPolygonClick(p0: Polygon?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: BoundaryContract.IPresenter) {
        this.presenter = presenter
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d("onMapReady", "In google onmapReady")
        presenter.setMapReady(p0!!, cityName)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_boundedmap, container, false)
        cityName = arguments!!.getString("CITYNAME")
        presenter = BoundaryPresenter(this, context!!, DataManager(LocalDataSource(), RemoteDataSource()))
        val boundaryFragment = childFragmentManager.findFragmentById(R.id.city_boundary) as SupportMapFragment
        boundaryFragment.getMapAsync(this)
        return view
    }
}