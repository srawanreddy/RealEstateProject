package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("area")
    @Expose
    var area: String? = null
    @SerializedName("area_unit")
    @Expose
    var areaUnit: String? = null
    @SerializedName("geo_key")
    @Expose
    var geoKey: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
