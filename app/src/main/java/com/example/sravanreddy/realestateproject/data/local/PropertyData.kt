package com.example.sravanreddy.realestateproject.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "propertyData")
data class PropertyData(@PrimaryKey(autoGenerate = true) var id: Long?,
                        @ColumnInfo(name = "propertyId") var propertyId: String?,
                        @ColumnInfo(name = "propertyName") var propertyName: String?,
                        @ColumnInfo(name = "propertyT") var propertyType: String?,
                        @ColumnInfo(name = "propertyC") var propertyCategory: String?,
                        @ColumnInfo(name = "propertyAdd1") var propertyAddr1: String?,
                        @ColumnInfo(name = "propertyAdd2") var propertyAddr2: String?,
                        @ColumnInfo(name = "propertyZip") var propertyZip: String?,
                        @ColumnInfo(name = "propertyImg1") var propertyImg1: String?,
                        @ColumnInfo(name = "propertyImg2") var propertyImg2: String?,
                        @ColumnInfo(name = "propertyImg3") var propertyImg3: String?,
                        @ColumnInfo(name = "propertyLat") var propertyLat: Double,
                        @ColumnInfo(name = "propertyLon") var propertyLon: Double,
                        @ColumnInfo(name = "propertyCost") var propertyCost: String?,
                        @ColumnInfo(name = "propertySize") var propertySize: String?,
                        @ColumnInfo(name = "propertyDesc") var propertyDes: String?,
                        @ColumnInfo(name = "propertyPub") var propertyPub: String?,
                        @ColumnInfo(name = "propertyMod") var propertyMod: String?,
                        @ColumnInfo(name = "propertySta") var propertySta: String?,
                        @ColumnInfo(name = "userId") var userId: String?) {
    constructor() : this(null, null, null, null, null, null, null
            , null, null, null, null, 0.0, 0.0, null, null, null,
            null, null, null, null)


}