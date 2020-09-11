package com.example.quakeapplication.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuakesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quakes: List<DatabaseQuake>)

    @Query("SELECT * FROM quakes WHERE mmi=:MMI")
    fun getQuakes(MMI: Double): LiveData<List<DatabaseQuake>>

    @Query("SELECT * FROM quakes WHERE publicID=:publicID")
    fun getQuake(publicID: String): DatabaseQuake

}

abstract class QuakesDatabase : RoomDatabase() {
    abstract val quakesDao: QuakesDao

    companion object {
        @Volatile
        private var INSTANCE: QuakesDatabase? = null

        fun getInstance(context: Context): QuakesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(context, QuakesDatabase::class.java, "quakes_database")
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}