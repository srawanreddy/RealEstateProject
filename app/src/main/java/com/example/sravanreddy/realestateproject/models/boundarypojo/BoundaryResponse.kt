package com.example.sravanreddy.realestateproject.models.boundarypojo


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BoundaryResponse(
    val response: Response
)

data class Response(
        val inputparameter: Inputparameter,
        val result: Result,
        val status: Status,
        val xmlns: String
)

data class Inputparameter(
    val latitude: String,
    val longitude: String,
    @SerializedName("package")
    @Expose
    val boundarypackage: String,
    val resource: String,
    val service: String
)

data class Result(
        @SerializedName("package")
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
    val area: String,
    val area_unit: String,
    val geo_key: String,
    val id: String,
    val name: String,
    val type: String
)

data class Status(
    val code: String,
    val long_description: String,
    val short_description: String
)