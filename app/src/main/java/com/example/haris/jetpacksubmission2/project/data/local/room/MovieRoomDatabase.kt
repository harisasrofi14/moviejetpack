package com.example.haris.jetpacksubmission2.project.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.haris.jetpacksubmission2.project.data.local.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieRoomDatabase::class.java,
                            "movie_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as MovieRoomDatabase
        }
    }
}