package com.example.quakeapplication.data

data class Quake(
    val publicID: String,
    val time: String,
    val depth: Double,
    val magnitude: Double,
    val mmi: Int,
    val locality: String,
    val quality: String,
    val latitude: Double,
    val longitude: Double

)