package com.example.sravanreddy.realestateproject.models

class PropertyData(var propertyName: String?, var propertyType:String?,
                   var address1:String?, var address2:String?, var lat: Double, var lon:Double,
                   var zipCode:String, var des:String?) {

    private var fireBaseDBID : String? = null

    fun setFirebaseDBId(key : String){
        this.fireBaseDBID = key
    }

    fun getFirebaseDBId() : String{
        return fireBaseDBID!!
    }


}