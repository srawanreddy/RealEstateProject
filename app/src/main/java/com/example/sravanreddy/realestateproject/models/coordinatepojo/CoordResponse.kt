package com.example.sravanreddy.realestateproject.models.coordinatepojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoordResponse(
        val response: Response
)

data class Response(
        val inputparameter: Inputparameter,
        val result: Result,
        val status: Status,
        val xmlns: String
)

data class Inputparameter(
        val AreaId: String,
        @SerializedName("package")
        @Expose
        val parameterpackage: String,
        val resource: String,
        val service: String
)

data class Result(
        @SerializedName("package")
        @Expose
        val resultpackage: Package,
        val xml_record: String
)

data class Package(
        val descr: String,
        val item: List<Item>,
        val name: String,
        val notice: String,
        val resource: String,
        val service: String,
        val version: String
)

data class Item(
        val boundary: String,
        val geo_center_latitude: String,
        val geo_center_longitude: String,
        val id: String,
        val type: String
)

data class Status(
        val code: String,
        val long_description: String,
        val short_description: String
)