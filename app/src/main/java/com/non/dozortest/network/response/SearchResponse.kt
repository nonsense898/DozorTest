package com.non.dozortest.network.response

import com.google.gson.annotations.SerializedName
import com.non.dozortest.data.entities.Movie

data class SearchResponse(
    @SerializedName("results")
    var results: List<Movie> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
