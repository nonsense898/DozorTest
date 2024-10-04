package com.non.dozortest.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.non.dozortest.dao.MovieDao
import com.non.dozortest.data.entities.Movie


@Database(entities = [Movie::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}