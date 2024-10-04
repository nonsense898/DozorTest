package com.non.dozortest.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.non.dozortest.dao.MovieDao
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.database.AppDatabase
import com.non.dozortest.paging.MoviePagingSource
import com.non.dozortest.network.ApiService
import com.non.dozortest.network.response.VideoResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext private val context: Context
) {
    private val roomDatabase: AppDatabase = AppDatabase.getDatabase(context)
    private val movieDao: MovieDao = roomDatabase.movieDAO()

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    fun isMovieSaved(movieId: Int): Flow<Boolean> = movieDao.isMovieSaved(movieId)

    fun getUsers(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 6,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { MoviePagingSource(apiService) }
    ).flow

    fun getMovieById(id: Int): Flow<Movie?> = movieDao.getMovieById(id)

    suspend fun deleteMovies() = withContext(Dispatchers.IO) {
        movieDao.deleteAll()
    }

    suspend fun fetchMovieVideos(movieId: Int): Response<VideoResponse> = withContext(Dispatchers.IO) {
        apiService.getMovieVideos(movieId)
    }

    suspend fun insertMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.insertMovie(movie)
    }

    suspend fun updateMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.updateMovie(movie)
    }

    suspend fun deleteMovieById(movieId: Int) = withContext(Dispatchers.IO) {
        movieDao.deleteMovie(movieId)
    }
}