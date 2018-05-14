package com.example.sravanreddy.realestateproject.models.propertybean



data class PropertyRes(
    val status: Status,
    val property: List<Property>
)

data class Property(
    val identifier: Identifier,
    val lot: Lot,
    val address: Address,
    val location: Location,
    val summary: Summary,
    val building: Building,
    val vintage: Vintage
)

data class Address(
    val country: String,
    val countrySubd: String,
    val line1: String,
    val line2: String,
    val locality: String,
    val matchCode: String,
    val oneLine: String,
    val postal1: String
)

data class Building(
    val size: Size,
    val rooms: Rooms
)

data class Rooms(
    val bathstotal: Float,
    val beds: Int
)

data class Size(
    val universalsize: Int
)

data class Lot(
    val lotSize1: Double
)

data class Location(
    val accuracy: String,
    val elevation: Int,
    val latitude: String,
    val longitude: String,
    val distance: Double,
    val geoid: String
)

data class Summary(
    val propclass: String,
    val propsubtype: String,
    val proptype: String,
    val yearbuilt: Int,
    val propLandUse: String,
    val propIndicator: String
)

data class Identifier(
    val obPropId: Long,
    val fips: String,
    val apn: String,
    val apnOrig: String
)

data class Vintage(
    val lastModified: String,
    val pubDate: String
)

data class Status(
    val version: String,
    val code: Int,
    val msg: String,
    val total: Int,
    val page: Int,
    val pagesize: Int
)