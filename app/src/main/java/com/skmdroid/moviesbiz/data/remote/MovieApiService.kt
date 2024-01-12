package com.skmdroid.moviesbiz.data.remote

import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): PopularMovies
}
