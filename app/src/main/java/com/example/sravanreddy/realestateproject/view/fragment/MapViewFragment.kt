package com.example.sravanreddy.realestateproject.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sravanreddy.realestateproject.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapview.view.*

class MapViewFragment : android.support.v4.app.Fragment(), OnMapReadyCallback {
    private lateinit var mapFragment : SupportMapFragment
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap!!.addMarker(MarkerOptions().position(LatLng(41.9047257, -88.3338299)).title("Current Location"))
        googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(41.9047257, -88.3338299)))
        googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(41.9047257, -88.3338299), 15.0f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_mapview, container, false)
        var mapFrament = childFragmentManager.findFragmentById(R.id.frameLayout_map_mapView) as? SupportMapFragment
        if(mapFrament == null) mapFrament = SupportMapFragment.newInstance()
        mapFrament!!.getMapAsync(this)
        return view
    }
}