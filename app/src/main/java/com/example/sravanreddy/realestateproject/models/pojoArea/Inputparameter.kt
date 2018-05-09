package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Inputparameter {
    @SerializedName("latitude")
    @Expose
    var latitude: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: String? = null
    @SerializedName("package")
    @Expose
    var `package`: String? = null
    @SerializedName("resource")
    @Expose
    var resource: String? = null
    @SerializedName("service")
    @Expose
    var service: String? = null

}
