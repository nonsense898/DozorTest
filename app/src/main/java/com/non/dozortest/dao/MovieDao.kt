package com.non.dozortest.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.non.dozortest.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId)")
    fun isMovieSaved(movieId: Int): Flow<Boolean>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    fun getMovieById(id: Int): Flow<Movie?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movieEntity: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovie(id: Int)
}