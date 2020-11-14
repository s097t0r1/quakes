package com.example.quakeapplication.data.source.remote

import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.data.source.local.DatabaseQuake
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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
    val mmi: Int,
    val locality: String,
    val quality: String
)

@JsonClass(generateAdapter = true)
data class StatisticNetworkContainer(
    val magnitudeCount: MagnitudeCount,
    val rate: Rate
)

@JsonClass(generateAdapter = true)
data class MagnitudeCount(
    @Json(name = "days365")
    val forYear: Map<String, Int>,

    @Json(name = "days28")
    val forMonth: Map<String, Int>,

    @Json(name = "days7")
    val forWeek: Map<String, Int>
)

@JsonClass(generateAdapter = true)
data class Rate(
    val perDay: Map<String, Int>
)

fun FeatureCollection.asDatabaseModel(): List<DatabaseQuake> {

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    return features.map {
        DatabaseQuake(
            publicID = it.properties.publicID,
            time = dateFormatter.parse(it.properties.time)!!.time,
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

fun FeatureCollection.isEmpty(): Boolean {
    return features.isEmpty()
}

fun FeatureCollection.isNotEmpty(): Boolean {
    return features.isNotEmpty()
}


fun FeatureCollection.asDomainModel(): Quake {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    features[0].apply {
        return Quake(
            publicID = this.properties.publicID,
            time = dateFormatter.parse(this.properties.time)!!,
            depth = this.properties.depth,
            magnitude = this.properties.magnitude,
            mmi = this.properties.mmi,
            locality = this.properties.locality,
            quality = this.properties.quality,
            latitude = this.geometry.coordinates[0],
            longitude = this.geometry.coordinates[1]
        )
    }
}
