package com.example.movieapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.MovieApi
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.models.Result
import com.example.movieapp.data.paging.MoviesDataSource
import com.example.movieapp.data.paging.NowPlayingMovieDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MovieApi
) {
    fun getMovies(): Flow<PagingData<Item>> {
        Log.d("TAG", "getMovies: ")
        return Pager(config = PagingConfig(
            pageSize = 2, enablePlaceholders = false, prefetchDistance = 20,
        ), pagingSourceFactory = {
            MoviesDataSource(moviesApi)
        }).flow
    }


    fun getNowPlaying():Flow<PagingData<Result>>{
        return Pager(config = PagingConfig(
            pageSize = 2,
            enablePlaceholders = false,
            prefetchDistance = 20
        ),
            pagingSourceFactory = {
                NowPlayingMovieDataSource(moviesApi)
            }).flow
    }

}