package com.non.dozortest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.non.dozortest.data.entities.Movie
import com.non.dozortest.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: ApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val response = api.getPopularMovies(page = page)
            val movies = response.body()?.results.orEmpty()

            val nextKey = if (movies.isEmpty()) null else page + 1

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
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