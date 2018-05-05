package com.example.sravanreddy.realestateproject.models

import java.io.Serializable

data class Property(val pId: String,
                    val name: String,
                    val type: String,
                    val category: String,
                    val address: String,
                    val imgUrl: ArrayList<String>,
                    val longitude: Double,
                    val latitude: Double,
                    val cost: Int,
                    val size: Int,
                    val description: String,
                    val zipCode: String,
                    val pubDate: String,
                    val modDate: String,
                    val status: String,
                    val userId: String) : Serializable {

}