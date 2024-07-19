package com.example.movieapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.MovieApi
import com.example.movieapp.data.models.BookmarkedMovie
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.models.Result
import com.example.movieapp.data.models.TopRatedResult
import com.example.movieapp.data.paging.MoviesDataSource
import com.example.movieapp.data.paging.NowPlayingMovieDataSource
import com.example.movieapp.data.paging.TopRatedDataResource
import com.example.movieapp.db.BookmarkDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MovieApi,
    private val bookmarkDatabase: BookmarkDatabase
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

    fun getTopRatedPlaying():Flow<PagingData<TopRatedResult>>{
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                prefetchDistance = 20
            ),
            pagingSourceFactory = {
                TopRatedDataResource(moviesApi)
            }
        ).flow
    }

    suspend fun insertSavedMovie(movie:Item) = bookmarkDatabase.bookMarkedMovieDao().insertItem(movie)

    suspend fun deleteSavedQuote(movieText:String) = bookmarkDatabase.bookMarkedMovieDao().deleteItem(movieText)

    suspend fun getAllSavedQuotes() = bookmarkDatabase.bookMarkedMovieDao().getAllBookMarks()

}