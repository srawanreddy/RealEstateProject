package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.sravanreddy.realestateproject.models.PropertyModel

@Dao
public interface PropertyDao {
    @Insert
    public fun insertProperty(propertyModel: PropertyTable)
    @Insert
    fun insertProperty(propertyModel: PropertyData)

    @Query("SELECT * FROM propertytable")
    public fun selectAllProperties(): List<PropertyTable>
    @Query("SELECT * FROM propertyData")
    fun getAll(): List<PropertyData>

    @Query("DELETE FROM propertytable WHERE propertyId = :proprtyId")
    public fun deleteAllProperties(proprtyId: String)

    @Query("DELETE from propertyData")
    fun deleteAll()
}