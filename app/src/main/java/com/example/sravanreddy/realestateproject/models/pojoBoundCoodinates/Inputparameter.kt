package com.example.sravanreddy.realestateproject.models.pojoBoundCoodinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Inputparameter {
    @SerializedName("AreaId")
    @Expose
    var areaId: String? = null
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
