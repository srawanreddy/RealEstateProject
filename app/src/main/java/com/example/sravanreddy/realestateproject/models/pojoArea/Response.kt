package com.example.sravanreddy.realestateproject.models.pojoArea

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("inputparameter")
    @Expose
    var inputparameter: Inputparameter? = null
    @SerializedName("result")
    @Expose
    var result: Result? = null
    @SerializedName("status")
    @Expose
    var status: Status? = null
    @SerializedName("xmlns")
    @Expose
    var xmlns: String? = null
}

