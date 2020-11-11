package com.example.quakeapplication.data.source.remote


import retrofit2.http.GET
import retrofit2.http.Query


interface QuakeApiService {
    @GET("quake")
    suspend fun getQuakes(@Query("MMI") MMI: Int): FeatureCollection

    @GET("quake/stats")
    suspend fun getStatistic(): StatisticNetworkContainer
}
