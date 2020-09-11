package com.example.quakeapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quakeapplication.domain.Quake

@Entity(tableName = "quakes")
data class DatabaseQuake(
    @PrimaryKey
    val publicID: String,
    val time: String,
    val depth: Double,
    val magnitude: Double,
    val mmi: Double,
    val locality: String,
    val quality: String,
    val longitude: Double,
    val latitude: Double
)

fun List<DatabaseQuake>.asDomainModel(): List<Quake> {
    return map {
        Quake (
            publicID = it.publicID,
            time = it.time,
            depth = it.depth,
            magnitude = it.magnitude,
            mmi = it.mmi,
            locality = it.locality,
            quality = it.quality,
            longitude = it.longitude,
            latitude = it.latitude
        )
    }
}