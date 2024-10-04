package com.non.dozortest.network.response


import com.google.gson.annotations.SerializedName
import com.non.dozortest.data.entities.Movie

data class MovieResponse(
    @SerializedName("results")
    var results: List<Movie>
)