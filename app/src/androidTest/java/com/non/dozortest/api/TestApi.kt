package com.non.dozortest.api

import com.non.dozortest.network.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieResponse>
}