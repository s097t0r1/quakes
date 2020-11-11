package com.example.quakeapplication.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quakeapplication.data.Quake
import java.text.DateFormat
import java.util.*

@Entity(tableName = "quakes")
data class DatabaseQuake(
    @PrimaryKey
    val publicID: String,
    val time: Long,
    val depth: Double,
    val magnitude: Double,
    val mmi: Int,
    val locality: String,
    val quality: String,
    val longitude: Double,
    val latitude: Double
)



fun List<DatabaseQuake>.asDomainModel(): List<Quake> {
    return map {
        Quake (
            publicID = it.publicID,
            time = Date(it.time),
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