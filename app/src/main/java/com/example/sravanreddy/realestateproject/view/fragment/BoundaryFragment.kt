package com.example.sravanreddy.realestateproject.view.fragment

import BoundaryContract
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sravanreddy.realestateproject.R
import com.example.sravanreddy.realestateproject.dialog.DialogDetail
import com.example.sravanreddy.realestateproject.models.propertybean.PropertyRes
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import java.util.*

class BoundaryFragment : Fragment(), View.OnClickListener, OnMapReadyCallback, BoundaryContract.IView {


    private var map: GoogleMap? = null

    private lateinit var cityName: String
    private lateinit var presenter: BoundaryContract.IPresenter
    private lateinit var progressDlg: ProgressDialog

    companion object {
        fun newInstance(cityName: String): BoundaryFragment {
            val bundle = Bundle()
            bundle.putString("cityname", cityName)
            val boundaryFragment = BoundaryFragment()
            boundaryFragment.arguments = bundle
            return boundaryFragment
        }
    }

    override fun setPresenter(presenter: BoundaryContract.IPresenter) {
        this.presenter = presenter
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        showProgressDlg()
        presenter.setMapReady(googleMap!!, cityName)
    }

    override fun onClick(v: View?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_boundedmap, container, false)

        cityName = arguments!!.getString("cityname")
        val mapFragment = childFragmentManager.findFragmentById(R.id.city_boundary) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun showArea(polygonOption1: PolygonOptions, citiesFound: ArrayList<LatLng>) {
        map!!.addPolygon(polygonOption1)
        map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(citiesFound[0], 9f))
        progressDlg.dismiss()
    }

    override fun showProgressDlg() {
        progressDlg = ProgressDialog(context)
        progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDlg.setMessage("Loading")
        progressDlg.setCancelable(false)
        progressDlg.setCanceledOnTouchOutside(true)
        progressDlg.show()
    }

    override fun showPropertyOnMap(propertyRes: PropertyRes) {
        // Add a marker
        for (i in 0 until propertyRes.property.size) {

            val propertyLatLng = LatLng(propertyRes.property[i].location.latitude.toDouble(), propertyRes.property[i].location.longitude.toDouble())

            val marker = map!!.addMarker(MarkerOptions().position(propertyLatLng).title(propertyRes.property[i].summary.propclass).snippet(propertyRes.property[i].address.oneLine))
            map!!.animateCamera(CameraUpdateFactory.zoomTo(8f))
            map!!.uiSettings.isZoomControlsEnabled = true
            map!!.uiSettings.setAllGesturesEnabled(true)
            map!!.uiSettings.isCompassEnabled = true

            marker.tag = i
        }

        map!!.setOnMarkerClickListener {

            presenter.markClick(it, propertyRes)
        }
    }

    override fun showDetailDialog(it: Marker?, propertyRes: PropertyRes) {
        val dialogDetail = DialogDetail.newInstance(propertyRes.property[it!!.tag!!.toString().toInt()])
        dialogDetail.showDialog(activity!!.supportFragmentManager, "dlgDetail")
    }
}