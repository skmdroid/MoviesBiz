package com.skmdroid.moviesbiz.di

import android.content.Context
import androidx.room.Room
import com.skmdroid.moviesbiz.data.local.AppDatabase
import com.skmdroid.moviesbiz.PopularMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase): PopularMovieDao {
        return database.popularMovieDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies_database"
        ).build()
    }
}