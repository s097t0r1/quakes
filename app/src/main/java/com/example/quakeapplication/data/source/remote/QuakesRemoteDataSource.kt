package com.example.quakeapplication.data.source.remote

import com.example.quakeapplication.data.Result
import com.example.quakeapplication.data.Success
import com.example.quakeapplication.data.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuakesRemoteDataSource @Inject constructor(
    private val quakeService: QuakeApiService
) {

    suspend fun getQuakes(MMI: Int): Result<FeatureCollection> = withContext(Dispatchers.IO) {
        try {

            val result = quakeService.getQuakes(MMI)

            if(result == null)
                return@withContext Error<Nothing>(Exception("Error retreiving data"))

            return@withContext Success(result)
        } catch (E: Exception) {
            return@withContext Error<Nothing>(E)
        }
    }

}