package com.non.dozortest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    val movies: Flow<PagingData<Movie>> = repository.getUsers().cachedIn(viewModelScope)

    val allMovies: StateFlow<List<Movie>> = repository.getAllMovies()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedMovieEntity = MutableStateFlow<Movie?>(null)
    val selectedMovieEntity: StateFlow<Movie?> = _selectedMovieEntity.asStateFlow()

    private val _videoDetails = MutableStateFlow<Map<String, String>>(emptyMap())
    val videoDetails: StateFlow<Map<String, String>> = _videoDetails.asStateFlow()


    fun getMovieVideos(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieVideos(movieId)
                if (response.isSuccessful) {
                    val videoResponse = response.body()
                    if (videoResponse != null) {
                        println(videoResponse.results)
                        _videoDetails.value = videoResponse.results.associate { it.name to it.key }
                    }
                } else {
                    println("API Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Network Error: ${e.message}")
            }
        }
    }

    fun selectMovie(movie: Movie) {
        _selectedMovieEntity.value = movie
    }

    fun isMovieSaved(movieId: Int): Flow<Boolean> {
        return repository.isMovieSaved(movieId)
    }

    fun insertMovie(movieEntity: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(movieEntity)
        }
    }

    fun updateMovie(movieEntity: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(movieEntity)
        }
    }

    fun removeMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovieById(movie.id)
        }
    }
}