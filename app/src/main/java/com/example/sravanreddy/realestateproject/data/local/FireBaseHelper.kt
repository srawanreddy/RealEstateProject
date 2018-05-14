package com.example.sravanreddy.realestateproject.data.local

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FireBaseHelper() {
    private var database:FirebaseDatabase?=null

    companion object {
        private var firebaseHelperIns:FireBaseHelper?=null
        fun getInstance():FireBaseHelper{
            if(firebaseHelperIns==null){
                firebaseHelperIns = FireBaseHelper()
            }
            return firebaseHelperIns!!
        }

    }

    fun getReference(nodeName:String):DatabaseReference{
        return database!!.getReference(nodeName)
    }

    init {
        database = FirebaseDatabase.getInstance()
    }

    fun addProperty(myRef:DatabaseReference, property:PropertyData){
        myRef.child("properytId").setValue(property.propertyId)
        myRef.child("propertyName").setValue(property.propertyName)
        myRef.child("propertyType").setValue(property.propertyType)
        myRef.child("propertyAddr1").setValue(property.propertyAddr1)
        myRef.child("propertyAddr2").setValue(property.propertyAddr2)
        myRef.child("latitude").setValue(property.propertyLat)
        myRef.child("longitude").setValue(property.propertyLon)
        myRef.child("publishDay").setValue(property.propertyPub)
    }

    fun getAllProperty(dataSnapshot: DataSnapshot) : List<PropertyData>{
        var properties:MutableList<PropertyData> = mutableListOf()
        for(snapshot:DataSnapshot in dataSnapshot.children){
            var pdata = snapshot.getValue(PropertyData::class.java)
            properties.add(pdata!!)
        }
        return properties

    }

}