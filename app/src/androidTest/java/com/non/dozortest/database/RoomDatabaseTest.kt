package com.non.dozortest.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.non.dozortest.dao.MovieDao
import com.non.dozortest.data.entities.Movie
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest : TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var roomDatabase: AppDatabase
    private lateinit var movieDao: MovieDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        roomDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = roomDatabase.movieDAO()
    }

    @After
    fun closeDb() {
        roomDatabase.close()
    }

    @Test
    fun insertAndRetrieveMovie() = runBlocking {
        val apiMovie = Movie(
            id = 1,
            originalTitle = "Inception",
            posterPath = "PosterPath",
            description = "Description",
            releaseDate = "releaseDate",
            voteAverage = "voteAverage",
            voteCount = "voteCount"
        )

        movieDao.insertMovie(apiMovie)

        val retrievedMovie = movieDao.getMovieById(1).first()
        assertThat(retrievedMovie, equalTo(apiMovie))
    }

    @Test
    fun deleteAndVerifyMovie() = runBlocking {
        val apiMovie = Movie(
            id = 1,
            originalTitle = "Inception",
            posterPath = "PosterPath",
            description = "Description",
            releaseDate = "releaseDate",
            voteAverage = "voteAverage",
            voteCount = "voteCount"
        )

        movieDao.insertMovie(apiMovie)
        movieDao.deleteMovie(apiMovie.id)

        val retrievedMovie = movieDao.getMovieById(1).first()
        assertThat(retrievedMovie, equalTo(null))
    }

    @Test
    fun updateAndRetrieveMovie() = runBlocking {
        val originalMovie = Movie(
            id = 1,
            originalTitle = "Inception",
            posterPath = "PosterPath",
            description = "Description",
            releaseDate = "releaseDate",
            voteAverage = "8.8",
            voteCount = "2000"
        )
        movieDao.insertMovie(originalMovie)

        val updatedMovie = Movie(
            id = 1,
            originalTitle = "Inception (Updated)",
            posterPath = "NewPosterPath",
            description = "Updated Description",
            releaseDate = "2024",
            voteAverage = "9.0",
            voteCount = "3000"
        )
        movieDao.updateMovie(updatedMovie)

        val retrievedMovie = movieDao.getMovieById(1).first()

        assertThat(retrievedMovie, equalTo(updatedMovie))
    }
}