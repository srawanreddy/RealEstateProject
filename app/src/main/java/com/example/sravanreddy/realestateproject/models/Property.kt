package com.example.sravanreddy.realestateproject.models

import java.io.Serializable

data class Property(val pId: String,
                    val name: String,
                    val type: String,
                    val category: String,
                    val address1: String,
                    val address2: String,
                    val zipCode: String,
                    val imgUrl1: String,
                    val imgUrl2: String,
                    val imgUrl3: String,
                    val latitude: Double,
                    val longitude: Double,
                    val cost: Double,
                    val size: Int,
                    val description: String,
                    val pubDate: String,
                    val modDate: String,
                    val status: String,
                    val userId: String,
                    var favorites: Int,
                    var watches: Int) : Serializable {

}