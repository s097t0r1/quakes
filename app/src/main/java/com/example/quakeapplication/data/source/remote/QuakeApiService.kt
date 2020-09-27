package com.example.quakeapplication.data.source.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL: String = "https://api.geonet.org.nz/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retorfit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface QuakeApiService {
    @GET("quake")
    suspend fun getQuakes(@Query("MMI") MMI: Int): FeatureCollection
}

object QuakeApi {
    val retrofitService: QuakeApiService by lazy {
        retorfit.create(QuakeApiService::class.java)
    }
}