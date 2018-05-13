package com.example.sravanreddy.realestateproject.view.fragment

import BoundaryContract
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sravanreddy.realestateproject.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_boundedmap.*

class BoundaryFragment : Fragment(), View.OnClickListener, OnMapReadyCallback, BoundaryContract.IView{
    private lateinit var cityName:String
    private lateinit var presenter: BoundaryContract.IPresenter

    companion object {
        fun newInstance(cityName: String):BoundaryFragment{
            val bundle = Bundle()
            bundle.putString("cityname", cityName)
            Log.d("CITYNAMEIS", cityName)
            val boundaryFragment = BoundaryFragment()
            boundaryFragment.arguments = bundle
            return boundaryFragment
        }
    }
    override fun setPresenter(presenter: BoundaryContract.IPresenter) {
        this.presenter = presenter
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d("CityName", cityName)
        mapProgress.visibility = View.VISIBLE
        presenter.setMapReady(p0!!, cityName)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_boundedmap, container, false)

        cityName = arguments!!.getString("cityname")
        Log.d("CityNameFragmentB", cityName)
        val mapFragment = childFragmentManager.findFragmentById(R.id.city_boundary) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    fun updateProgressBar(num:Int){
        if(num==0){
            mapProgress.visibility = View.VISIBLE
        }
        else{
            mapProgress.visibility = View.INVISIBLE
        }
    }

}