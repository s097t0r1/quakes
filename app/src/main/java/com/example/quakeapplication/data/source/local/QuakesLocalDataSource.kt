package com.example.quakeapplication.data.source.local

import com.example.quakeapplication.data.Error
import com.example.quakeapplication.data.Result
import com.example.quakeapplication.data.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuakesLocalDataSource @Inject constructor(
    private val DB: QuakesDatabase
) {

    suspend fun getQuakes(MMI: Int): Result<List<DatabaseQuake>> = withContext(Dispatchers.IO) {
            try {
                val result = DB.quakesDao.getQuakes(MMI)
                return@withContext Success(result)
            } catch (e: Exception) {
                return@withContext Error<Nothing>(e)
            }
    }

    suspend fun getQuake(publicID: String): Result<DatabaseQuake> = withContext(Dispatchers.IO) {
        try {
            val result = DB.quakesDao.getQuake(publicID)
            return@withContext Success(result)
        } catch (E: Exception) {
            return@withContext Error<Nothing>(E)
        }
    }

    suspend fun deleteAllQuakes() = withContext(Dispatchers.IO) {
        DB.quakesDao.deleteQuakes()
    }

    suspend fun insertAllQuakes(quakes: List<DatabaseQuake>) = withContext(Dispatchers.IO) {
        DB.quakesDao.insertQuakes(quakes)
    }

}