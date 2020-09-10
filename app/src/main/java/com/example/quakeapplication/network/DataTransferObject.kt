package com.example.quakeapplication.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FeatureCollection(val features: List<Feature>) {
    public operator fun get(index: Int) = features[index]
}


@JsonClass(generateAdapter = true)
class Feature(
    val geometry: Point,
    val properties: Properties
)

@JsonClass(generateAdapter = true)
class Point(val coordinates: Array<Double>)

@JsonClass(generateAdapter = true)
data class Properties(
    val publicID: String,
    val time: String,
    val depth: Double,
    val magnitude: Double,
    val mmi: Double,
    val locality: String,
    val quality: String
)