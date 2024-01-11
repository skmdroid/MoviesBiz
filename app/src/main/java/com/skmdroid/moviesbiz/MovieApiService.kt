package com.skmdroid.moviesbiz

import com.skmdroid.moviesbiz.model.PopularMovies
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): PopularMovies
}
