package com.example.quakeapplication.network

import com.example.quakeapplication.database.DatabaseQuake
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FeatureCollection(
    val features: List<Feature>
)


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

fun FeatureCollection.asDatabaseModel(): List<DatabaseQuake> {
    return features.map {
        DatabaseQuake(
            publicID = it.properties.publicID,
            time = it.properties.time,
            depth = it.properties.depth,
            magnitude = it.properties.magnitude,
            mmi = it.properties.mmi,
            locality = it.properties.locality,
            quality = it.properties.quality,
            latitude = it.geometry.coordinates[0],
            longitude = it.geometry.coordinates[1]
        )
    }
}