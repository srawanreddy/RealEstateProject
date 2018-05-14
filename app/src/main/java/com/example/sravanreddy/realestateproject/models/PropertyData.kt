package com.example.sravanreddy.realestateproject.models

class PropertyData(var propertyId: String?, var propertyName: String?, var propertyType:String?,
                   var address1:String?, var address2:String?, var lat: Double, var lon:Double,
                   var zipCode:String, var des:String?) {
    constructor():this("", "", "",
                       "", "", 0.0, 0.0, "", "")

    constructor(name: String, type: String, add1: String, add2: String, lat: Double, lng: Double, zcode: String, des: String) : this()
}