package com.example.quakeapplication.data.source.local

import android.content.Context
import androidx.room.*

@Dao
interface QuakesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuakes(quakes: List<DatabaseQuake>)

    @Query("SELECT * FROM quakes WHERE mmi=:MMI")
    fun getQuakes(MMI: Int): List<DatabaseQuake>

    @Query("SELECT * FROM quakes WHERE publicID=:publicID")
    fun getQuake(publicID: String): DatabaseQuake

    @Query("DELETE FROM quakes")
    fun deleteQuakes()

}

@Database(entities = [DatabaseQuake::class], version = 2, exportSchema = false)
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