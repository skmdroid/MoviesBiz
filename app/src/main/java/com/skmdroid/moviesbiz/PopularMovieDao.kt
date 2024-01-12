package com.skmdroid.moviesbiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skmdroid.moviesbiz.data.local.MovieEntity

@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM popular_movies ORDER BY vote_average ASC")
    suspend fun getPopularMovies(): List<MovieEntity>

    @Query("SELECT * FROM popular_movies where id = :id LIMIT 1")
    suspend fun getMovieDetail(id: Int): MovieEntity?

    @Query("DELETE FROM popular_movies")
    suspend fun clearMovies()
}