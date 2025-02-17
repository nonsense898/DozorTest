package com.non.dozortest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.network.ApiService
import com.non.dozortest.network.response.MovieResponse
import com.non.dozortest.network.response.SearchResponse

class MoviePagingSource(
    private val api: ApiService,
    private val searchQuery: String,
    private val genre: Int?
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = when {
                searchQuery.isBlank() && genre == null -> api.getPopularMovies(page = page)
                searchQuery.isNotBlank() -> api.searchMoviesByQuery(page, searchQuery)
                genre != null -> api.getMoviesByGenre(page = page, genre = genre)
                else -> api.getPopularMovies(page = page)
            }

            if (!response.isSuccessful) {
                return LoadResult.Error(Exception("API Error: ${response.code()} ${response.message()}"))
            }
            val movieResponse = response.body()
            val movies = when {
                searchQuery.isNullOrEmpty() && genre == null && movieResponse is MovieResponse -> movieResponse.results
                searchQuery.isNotEmpty() && movieResponse is SearchResponse -> movieResponse.results
                movieResponse is MovieResponse -> movieResponse.results
                else -> emptyList()
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}