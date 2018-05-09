package com.example.sravanreddy.realestateproject.models.pojoBoundCoodinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("package")
    @Expose
    var `package`: Package? = null
    @SerializedName("xml_record")
    @Expose
    var xmlRecord: String? = null
}
