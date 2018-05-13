package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Room
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity
class PropertyTable( propertyId: String,
                     propertyName: String,
                     propertyType: String,
                     propertyCategory: String,
                     propertyAddress1: String,
                     propertyAddress2: String,
                     propertyZip: String,
                     propertyImage1: String,
                     propertyImage2: String,
                     propertyImage3: String,
                     propertyLatitue: Double,
                     propertyLongitude: Double ,
                     propertyCost: String,
                     propertySize: String,
                     propertyDesc: String,
                     propertyPublishDate: String,
                     propertyModifyDate: String,
                     propertStatus: String,
                     userId: String) {
    @PrimaryKey
    @NotNull
    var propertyId: String? = null
    var propertyName: String? = null
    var propertyType: String? = null
    var propertyCategory: String? = null
    var propertyAddress1: String? = null
    var propertyAddress2: String? = null
    var propertyZip: String? = null
    var propertyImage1: String? = null
    var propertyImage2: String? = null
    var propertyImage3: String? = null
    var propertyLatitue: Double = 0.toDouble()
    var propertyLongitude: Double = 0.toDouble()
    var propertyCost: String? = null
    var propertySize: String? = null
    var propertyDesc: String? = null
    var propertyPublishDate: String? = null
    var propertyModifyDate: String? = null
    var propertStatus: String? = null
    var userId: String? = null


}