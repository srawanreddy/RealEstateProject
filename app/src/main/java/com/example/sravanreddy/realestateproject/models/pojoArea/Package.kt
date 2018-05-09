package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Package {

    @SerializedName("descr")
    @Expose
    var descr: String? = null
    @SerializedName("item")
    @Expose
    var item: List<Item>? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("notice")
    @Expose
    var notice: String? = null
    @SerializedName("resource")
    @Expose
    var resource: String? = null
    @SerializedName("service")
    @Expose
    var service: String? = null
    @SerializedName("version")
    @Expose
    var version: String? = null

}