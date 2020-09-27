package com.example.quakeapplication.di

import android.content.Context
import com.example.quakeapplication.data.source.local.QuakesDatabase
import com.example.quakeapplication.data.source.remote.QuakeApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    fun provideDatabase(context: Context): QuakesDatabase =
        QuakesDatabase.getInstance(context)

    @JvmStatic
    @Provides
    fun provideRetrofitService(moshi: Moshi): QuakeApiService {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("https://api.geonet.org.nz/")
                .build()
                .create(QuakeApiService::class.java)
    }

    @JvmStatic
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

}

