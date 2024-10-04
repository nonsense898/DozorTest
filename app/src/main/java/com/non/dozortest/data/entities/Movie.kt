package com.non.dozortest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var description: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: String,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: String,

    @ColumnInfo(name = "isSaved")
    var isSaved: Boolean = false
)
