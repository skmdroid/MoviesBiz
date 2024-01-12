package com.skmdroid.moviesbiz

import android.util.Log
import com.skmdroid.moviesbiz.data.local.MovieEntity
import com.skmdroid.moviesbiz.data.remote.MovieApiService
import com.skmdroid.moviesbiz.data.remote.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDao: PopularMovieDao
) {
    suspend fun getPopularMovies(
        forceRefresh: Boolean = false
    ): List<Result> {
        if (forceRefresh) {
            Log.d("Test4", "force refresh")
            return pullFromRemote()
        }
        val cachedMovies = movieDao.getPopularMovies()
        if (cachedMovies.isNotEmpty()) {
            Log.d("Test4", "already cached")
            return cachedMovies.map { it.toResult() }
        }

        Log.d("Test4", "not cached")
        return pullFromRemote()
    }

    private suspend fun pullFromRemote(): List<Result> {
        val movies = movieApiService.getPopularMovies().results
        val movieEntities = movies.map { it.toMovieEntity() }
        movieDao.clearMovies()
        movieDao.insertMovies(movieEntities)
        return movies
    }

    suspend fun getMovieDetail(id: Int): Result? {
        val result = movieDao.getMovieDetail(id)
        result?.let { movieEntity ->
            return movieEntity.toResult()
        } ?: run {
            return null
        }
    }

    private fun MovieEntity.toResult(): Result {
        return Result(
            id = id,
            voteCount = voteCount,
            video = video,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            posterPath = posterPath,
            popularity = popularity,
            originalTitle = originalTitle,
            overview = overview,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            backdropPath = backdropPath,
            adult = adult,
            title = title
        )
    }

    private fun Result.toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            voteCount = voteCount,
            video = video,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            posterPath = posterPath,
            popularity = popularity,
            originalTitle = originalTitle,
            overview = overview,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            backdropPath = backdropPath,
            adult = adult,
            title = title
        )
    }
}