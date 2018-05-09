package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BoundaryResponse {
    @SerializedName("response")
    @Expose
    var response: Response? = null

}