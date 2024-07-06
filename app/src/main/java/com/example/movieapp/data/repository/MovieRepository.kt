package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.TodoApi
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.paging.MoviesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: TodoApi
) {

    fun getMoviesList(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesDataSource(api) }
        ).flow
    }

}