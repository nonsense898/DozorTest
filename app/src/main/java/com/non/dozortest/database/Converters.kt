package com.non.dozortest.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromGenreList(genreIds: List<Int>): String {
        return genreIds.joinToString(",")
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<Int> {
        return if (genreString.isEmpty()) emptyList() else genreString.split(",").map { it.toInt() }
    }
}