package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("long_description")
    @Expose
    var longDescription: String? = null
    @SerializedName("short_description")
    @Expose
    var shortDescription: String? = null

}
