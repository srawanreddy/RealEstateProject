package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.sravanreddy.realestateproject.models.PropertyModel

@Dao
interface PropertyDao {
    @Insert
    fun insertProperty(propertyModel: PropertyData)

    @Query("SELECT * FROM propertyData")
    fun getAll(): List<PropertyData>

    @Query("DELETE from propertyData")
    fun deleteAll()
}