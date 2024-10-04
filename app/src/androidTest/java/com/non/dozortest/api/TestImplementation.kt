package com.non.dozortest.api

import com.non.dozortest.di.NetworkModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestImplementation {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YTlmNWU4ZmZhMGE3MzQyMzE1NTA0ZjYxZDc2ZGU4YSIsIm5iZiI6MTcyNjg0MDMwNy40NzMwMTYsInN1YiI6IjY2MjkzZmQ1NjNkOTM3MDE2NDc0MWI2NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cgP1YiJVTaJued9SlJZrnSRydhHaE2OyksPjIgPmOOE")
                .header("Accept", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    fun provideApi(): TestApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TestApi::class.java)
}