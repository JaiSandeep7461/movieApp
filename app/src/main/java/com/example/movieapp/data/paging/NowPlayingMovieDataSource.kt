package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.MovieApi
import com.example.movieapp.data.models.Result

class NowPlayingMovieDataSource(
    private val moviesApi:MovieApi
) :PagingSource<Int,Result>(){

    companion object{
        private const val STARTING_PAGE_INDEX=1
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val position = params.key?: STARTING_PAGE_INDEX
            val response = moviesApi.getNowPlaying(position)
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if(response.results.isEmpty()) null else position+1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

}