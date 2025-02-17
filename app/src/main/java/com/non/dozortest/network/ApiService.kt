package com.non.dozortest.network

import com.non.dozortest.network.response.MovieResponse
import com.non.dozortest.network.response.SearchResponse
import com.non.dozortest.network.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMoviesByQuery(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("language") language: String = "en-US"
    ): Response<SearchResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("page") page: Int,
        @Query("sort_by") sort: String = "popularity.desc",
        @Query("with_genres") genre: Int? = null,
        @Query("language") language: String = "en-US"
    ): Response<MovieResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Response<VideoResponse>
}