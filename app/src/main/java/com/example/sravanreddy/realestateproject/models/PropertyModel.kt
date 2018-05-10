package com.example.sravanreddy.realestateproject.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PropertyModel() : Parcelable{
        @SerializedName("Property Id")
        private var propertyId: String? = null
        @SerializedName("Property Name")
        private var propetyName: String? = null
        @SerializedName("Property Type")
        private var propertyType: String? = null
        @SerializedName("Property Category")
        private var propertyCategory: String? = null
        @SerializedName("Property Address1")
        private var propertyAddress1: String? = null
        @SerializedName("Property Address2")
        private var propertyAddress2: String? = null
        @SerializedName("Property Zip")
        private var propertyZip: String? = null
        @SerializedName("Property Image 1")
        private var propertyImage1: String? = null
        @SerializedName("Property Image 2")
        private var propertyImage2: String? = null
        @SerializedName("Property Image 3")
        private var propertyImage3: String? = null
        @SerializedName("Property Latitude")
        private var propertyLatitue: Double = 0.toDouble()
        @SerializedName("Property Longitude")
        private var propertyLongitude: Double = 0.toDouble()
        @SerializedName("Property Cost")
        private var propertyCost: String? = null
        @SerializedName("Property Size")
        private var propertySize: String? = null
        @SerializedName("Property Desc")
        private var propertyDesc: String? = null
        @SerializedName("Property Published Date")
        private var propertyPublishDate: String? = null
        @SerializedName("Property Modify Date")
        private var propertyModifyDate: String? = null
        @SerializedName("Property Status")
        private var propertStatus: String? = null
        @SerializedName("User Id")
        private var userId: String? = null

    constructor(parcel: Parcel) : this() {
        propertyId = parcel.readString()
        propetyName = parcel.readString()
        propertyType = parcel.readString()
        propertyCategory = parcel.readString()
        propertyAddress1 = parcel.readString()
        propertyAddress2 = parcel.readString()
        propertyZip = parcel.readString()
        propertyImage1 = parcel.readString()
        propertyImage2 = parcel.readString()
        propertyImage3 = parcel.readString()
        propertyLatitue = parcel.readDouble()
        propertyLongitude = parcel.readDouble()
        propertyCost = parcel.readString()
        propertySize = parcel.readString()
        propertyDesc = parcel.readString()
        propertyPublishDate = parcel.readString()
        propertyModifyDate = parcel.readString()
        propertStatus = parcel.readString()
        userId = parcel.readString()
    }

    fun getPropertyId(): String? {
            return propertyId
        }

        fun setPropertyId(propertyId: String) {
            this.propertyId = propertyId
        }

        fun getPropetyName(): String? {
            return propetyName
        }

        fun setPropetyName(propetyName: String) {
            this.propetyName = propetyName
        }

        fun getPropertyType(): String? {
            return propertyType
        }

        fun setPropertyType(propertyType: String) {
            this.propertyType = propertyType
        }

        fun getPropertyCategory(): String? {
            return propertyCategory
        }

        fun setPropertyCategory(propertyCategory: String) {
            this.propertyCategory = propertyCategory
        }

        fun getPropertyAddress1(): String? {
            return propertyAddress1
        }

        fun setPropertyAddress1(propertyAddress1: String) {
            this.propertyAddress1 = propertyAddress1
        }

        fun getPropertyAddress2(): String? {
            return propertyAddress2
        }

        fun setPropertyAddress2(propertyAddress2: String) {
            this.propertyAddress2 = propertyAddress2
        }

        fun getPropertyZip(): String? {
            return propertyZip
        }

        fun setPropertyZip(propertyZip: String) {
            this.propertyZip = propertyZip
        }

        fun getPropertyImage1(): String? {
            return propertyImage1
        }

        fun setPropertyImage1(propertyImage1: String) {
            this.propertyImage1 = propertyImage1
        }

        fun getPropertyImage2(): String? {
            return propertyImage2
        }

        fun setPropertyImage2(propertyImage2: String) {
            this.propertyImage2 = propertyImage2
        }

        fun getPropertyImage3(): String? {
            return propertyImage3
        }

        fun setPropertyImage3(propertyImage3: String) {
            this.propertyImage3 = propertyImage3
        }

        fun getPropertyLatitue(): Double {
            return propertyLatitue
        }

        fun setPropertyLatitue(propertyLatitue: Double) {
            this.propertyLatitue = propertyLatitue
        }

        fun getPropertyLongitude(): Double {
            return propertyLongitude
        }

        fun setPropertyLongitude(propertyLongitude: Double) {
            this.propertyLongitude = propertyLongitude
        }

        fun getPropertyCost(): String? {
            return propertyCost
        }

        fun setPropertyCost(propertyCost: String) {
            this.propertyCost = propertyCost
        }

        fun getPropertySize(): String? {
            return propertySize
        }

        fun setPropertySize(propertySize: String) {
            this.propertySize = propertySize
        }

        fun getPropertyDesc(): String? {
            return propertyDesc
        }

        fun setPropertyDesc(propertyDesc: String) {
            this.propertyDesc = propertyDesc
        }

        fun getPropertyPublishDate(): String? {
            return propertyPublishDate
        }

        fun setPropertyPublishDate(propertyPublishDate: String) {
            this.propertyPublishDate = propertyPublishDate
        }

        fun getPropertyModifyDate(): String? {
            return propertyModifyDate
        }

        fun setPropertyModifyDate(propertyModifyDate: String) {
            this.propertyModifyDate = propertyModifyDate
        }

        fun getPropertStatus(): String? {
            return propertStatus
        }

        fun setPropertStatus(propertStatus: String) {
            this.propertStatus = propertStatus
        }

        fun getUserId(): String? {
            return userId
        }

        fun setUserId(userId: String) {
            this.userId = userId
        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(propertyId)
        parcel.writeString(propetyName)
        parcel.writeString(propertyType)
        parcel.writeString(propertyCategory)
        parcel.writeString(propertyAddress1)
        parcel.writeString(propertyAddress2)
        parcel.writeString(propertyZip)
        parcel.writeString(propertyImage1)
        parcel.writeString(propertyImage2)
        parcel.writeString(propertyImage3)
        parcel.writeDouble(propertyLatitue)
        parcel.writeDouble(propertyLongitude)
        parcel.writeString(propertyCost)
        parcel.writeString(propertySize)
        parcel.writeString(propertyDesc)
        parcel.writeString(propertyPublishDate)
        parcel.writeString(propertyModifyDate)
        parcel.writeString(propertStatus)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PropertyModel> {
        override fun createFromParcel(parcel: Parcel): PropertyModel {
            return PropertyModel(parcel)
        }

        override fun newArray(size: Int): Array<PropertyModel?> {
            return arrayOfNulls(size)
        }
    }
}