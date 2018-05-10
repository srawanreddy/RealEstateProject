package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Room
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity
class PropertyTable() {
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