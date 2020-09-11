package com.example.quakeapplication.domain

data class Quake(
    val publicID: String,
    val time: String,
    val depth: Double,
    val magnitude: Double,
    val mmi: Double,
    val locality: String,
    val quality: String,
    val latitude: Double,
    val longitude: Double

)