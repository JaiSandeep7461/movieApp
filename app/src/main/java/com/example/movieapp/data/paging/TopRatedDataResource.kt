package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.MovieApi
import com.example.movieapp.data.models.TopRatedResult

class TopRatedDataResource(
    private val movieApi: MovieApi
) :PagingSource<Int,TopRatedResult>(){

    companion object{
        private const val STARTING_PAGE_INDEX=1
    }
    override fun getRefreshKey(state: PagingState<Int, TopRatedResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedResult> {
        try {
            val position = params.key?: STARTING_PAGE_INDEX
            val response = movieApi.getTopRated(position)
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isEmpty()) null else position+1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
}