package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sravanreddy.realestateproject.models.PropertyModel

@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProperty(propertyModel: PropertyData)

    @Insert
    fun insertAll(properties: ArrayList<PropertyData>)

    @Query("SELECT * FROM property_data")
    fun getAll(): List<PropertyData>

    @Query("DELETE from property_data")
    fun deleteAll()
}