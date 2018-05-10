package com.example.sravanreddy.realestateproject.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.models.PropertyModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapview.view.*

class MapViewFragment : android.support.v4.app.Fragment(), OnMapReadyCallback {
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var propertyModels : ArrayList<PropertyModel>
    override fun onMapReady(googleMap: GoogleMap?) {
        for(i in 0 until propertyModels.size){
            googleMap!!.addMarker(MarkerOptions().position(LatLng(propertyModels.get(i).getPropertyLatitue(), propertyModels.get(i).getPropertyLongitude())).title(propertyModels.get(i).getPropertyDesc()))
        }
//        googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(propertyModels.get(0).getPropertyLongitude(), propertyModels.get(0).getPropertyLongitude())))
//        googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(propertyModels.get(0).getPropertyLongitude(), propertyModels.get(0).getPropertyLongitude()), 15.0f))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        propertyModels = arguments!!.getParcelableArrayList("Property Model")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_mapview, container, false)
        var mapFrament = childFragmentManager.findFragmentById(R.id.frameLayout_map_mapView) as? SupportMapFragment
        if(mapFrament == null) mapFrament = SupportMapFragment.newInstance()
        mapFrament!!.getMapAsync(this)
        return view
    }
}