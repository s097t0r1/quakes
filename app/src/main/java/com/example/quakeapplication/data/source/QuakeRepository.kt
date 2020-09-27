package com.example.quakeapplication.data.source

import com.example.quakeapplication.data.Error
import com.example.quakeapplication.data.Result
import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.data.Success
import com.example.quakeapplication.data.source.local.DatabaseQuake
import com.example.quakeapplication.data.source.local.QuakesLocalDataSource
import com.example.quakeapplication.data.source.local.asDomainModel
import com.example.quakeapplication.data.source.remote.QuakesRemoteDataSource
import com.example.quakeapplication.data.source.remote.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class QuakeRepository @Inject constructor(
    val localDataSource: QuakesLocalDataSource,
    val remoteDataSource: QuakesRemoteDataSource
) {

    suspend fun getQuakes(MMI: Int, foreUpdate: Boolean): Result<List<Quake>> = withContext(Dispatchers.IO) {
        val remoteResult = remoteDataSource.getQuakes(MMI)

        if(remoteResult is Success) {
            refreshLocalDataSource(remoteResult.data.asDatabaseModel())
        } else {
            if(foreUpdate)
                return@withContext Error<Nothing>(Exception("Cannot getting a remote data"))
        }


        val localResult = localDataSource.getQuakes(MMI)

        if(localResult is Success)
            return@withContext Success(localResult.data.asDomainModel())
        else
            return@withContext Error<Nothing>(Exception("Database is empty"))
    }

    private suspend fun refreshLocalDataSource(localData: List<DatabaseQuake>) = withContext(Dispatchers.IO) {
        localDataSource.deleteAllQuakes()
        localDataSource.insertAllQuakes(localData)
    }

}

