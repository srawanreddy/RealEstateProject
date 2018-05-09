package com.example.sravanreddy.realestateproject.models.pojoBoundCoodinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("boundary")
    @Expose
    var boundary: String? = null
    @SerializedName("geo_center_latitude")
    @Expose
    var geoCenterLatitude: String? = null
    @SerializedName("geo_center_longitude")
    @Expose
    var geoCenterLongitude: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
