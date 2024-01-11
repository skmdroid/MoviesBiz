package com.skmdroid.moviesbiz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skmdroid.moviesbiz.PopularMovieDao

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1, exportSchema = false
)
@TypeConverters(IntListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularMovieDao(): PopularMovieDao
}