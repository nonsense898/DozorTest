package com.non.dozortest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val query = MutableStateFlow<String?>(null)
    private val selectedGenre = MutableStateFlow<Int?>(null)

    fun setQuery(newQuery: String?) { query.value = newQuery }

    fun setGenre(newGenre: Int?) { selectedGenre.value = newGenre }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val movies: Flow<PagingData<Movie>> = combine(query, selectedGenre) { query, genre ->
        query to genre
    }
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { (query, genre) ->
            repository.getMovies(query = query ?: "", genre = genre)
        }
        .cachedIn(viewModelScope)

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
                    response.body()?.let { videoResponse ->
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

    fun removeMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovieById(movie.id)
        }
    }
}