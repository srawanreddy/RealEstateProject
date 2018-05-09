package com.example.sravanreddy.realestateproject.models.pojoBoundCoodinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoordResponse {
    @SerializedName("response")
    @Expose
    var response: Response? = null
}
