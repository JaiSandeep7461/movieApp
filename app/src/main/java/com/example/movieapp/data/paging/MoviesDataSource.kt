package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.TodoApi
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.models.ItemResponse

class MoviesDataSource(private val moviesApi:TodoApi)  : PagingSource<Int,Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val position = params.key ?: 1
            val response = moviesApi.getMoviesList(position)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if(response.results.isEmpty()) null else position+1
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}