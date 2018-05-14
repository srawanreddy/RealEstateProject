package com.example.sravanreddy.realestateproject.view.fragment

import BoundaryContract
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import com.example.sravanreddy.realestateproject.data.DataManager
import com.example.sravanreddy.realestateproject.data.IDataSource
import com.example.sravanreddy.realestateproject.models.coordinatepojo.CoordResponse
import com.example.sravanreddy.realestateproject.models.coordinatepojo.Item
import com.example.sravanreddy.realestateproject.models.propertybean.PropertyRes
import com.example.sravanreddy.realestateproject.util.longestBound
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PolygonOptions
import java.io.IOException
import java.util.*

class BoundaryPresenter(private var boundaryFragment: BoundaryFragment, var context: Context, dataManager: DataManager) : BoundaryContract.IPresenter {

    private var mContext: Context = context
    var mManager = dataManager
    private var coordList: List<LatLng>? = null
    private var areaId: String? = null
    lateinit var cordItem: List<Item>
    private var fragmentView = boundaryFragment

    override fun start() {
    }

    init {
        fragmentView.setPresenter(this)
    }

    override fun setMapReady(p0: GoogleMap, cityName: String) {
        val citiesFound: ArrayList<LatLng> = obtainCityLatLng(cityName, mContext)
        mManager.getAreaData(cityName, citiesFound[0], object : IDataSource.NetworkCallBack {
            override fun onSubscribe() {

            }

            override fun onSuccess(response: Any, areaId: String) {
                Log.d("BoundaryPresenter", "Received Coordinates List")
                val coordResponse = response as CoordResponse
                cordItem = coordResponse.response.result.resultpackage.item
                this@BoundaryPresenter.areaId = areaId

                coordList = parseCoordinates(cordItem, areaId)

                val polygonOption1 = PolygonOptions()
                polygonOption1.addAll(coordList)
                fragmentView.showArea(polygonOption1, citiesFound)
            }

            override fun onSuccess(response: Any) {

            }

            override fun onFailure(t: Throwable) {
                Log.i("BoundaryPresenter", t.message)
            }
        })

    }

    private fun convertCoordinates(locationInfo: String): List<LatLng> {
        val coordinates = ArrayList<LatLng>()
        val tmpRes: List<String> = locationInfo.split(",").map { it.trim() }
        tmpRes.forEach {
            val spacePos = it.indexOf(' ')
            val val1: Double = it.substring(spacePos + 1, it.length).toDouble()
            val val2: Double = it.substring(0, spacePos).toDouble()
            val coord = LatLng(val1, val2)
            coordinates.add(coord)
        }
        return coordinates
    }

    private fun obtainCityLatLng(cityName: String, context: Context): ArrayList<LatLng> {
        val result = ArrayList<LatLng>()
        try {
            val geocoder = Geocoder(context)
            val addresses: List<Address> = geocoder.getFromLocationName(cityName, 4)
            if (addresses.isNotEmpty()) {
                for (i in 0 until addresses.size) {
                    if (addresses[i].countryName == "United States") {
                        val latitude: Double = addresses[i].latitude
                        val longitude: Double = addresses[i].longitude
                        Log.i("邮编", "$latitude")
                        Log.i("邮编", "$longitude")

                        getPropertyInArea(latitude, longitude)
                        result.add(LatLng(latitude, longitude))
                    }
                }
            }
            return result
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun getPropertyInArea(latitude: Double, longitude: Double) {
        mManager.getPropertyInArea(latitude, longitude, object : IDataSource.NetworkCallBack {
            override fun onSubscribe() {
            }

            override fun onSuccess(response: Any) {
                val propertyRes = response as PropertyRes
                Log.i("getPropertyInArea_onSuccess", "${response.property.size}")
                fragmentView.showPropertyOnMap(propertyRes)
            }

            override fun onSuccess(response: Any, areaId: String) {
            }

            override fun onFailure(t: Throwable) {
                Log.i("getPropertyInArea_onError", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun parseCoordinates(itemList: List<Item>, areaId: String): List<LatLng> {
        var points: String? = null
        val iter = itemList.listIterator()

        while (iter.hasNext()) {
            val item: com.example.sravanreddy.realestateproject.models.coordinatepojo.Item = iter.next()
            if (item.id == areaId) {
                val boundary: String = item.boundary
                if (boundary.contains("MULTIPOLYGON"))
                    points = boundary.substringAfter("MULTIPOLYGON ")
                if (boundary.contains("POLYGON "))
                    points = boundary.substringAfter("POLYGON ")
                break
            }
        }
        return parsePolygons(points!!)
    }

    /**
     * Asssume this polygon has only one item here, polyType is 0
     */
    fun parsePolygons(poly: String): List<LatLng> {

        val purePoints: String = longestBound(poly)
        return convertCoordinates(purePoints)
    }

    override fun markClick(it: Marker?, propertyRes: PropertyRes): Boolean {
        fragmentView.showDetailDialog(it, propertyRes)
        return false
    }


}