package com.example.movieapp.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.models.popular.Result
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieResponse = MutableStateFlow<Resource<PagingData<Result>>>(Resource.Loading())
    val movieResponse: MutableStateFlow<Resource<PagingData<Result>>> = _movieResponse


    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                repository.getMovies().cachedIn(viewModelScope).collect { pagingData ->
                    _movieResponse.value = Resource.Success(pagingData)
                }
            } catch (e: Exception) {
                _movieResponse.value = Resource.Error(e)
            }
        }
    }

    fun getAllRecords() {
        viewModelScope.launch {
            Log.e("17072024", "get db  data ${repository.getAllSavedQuotes().size}")
            return@launch
        }
    }

    fun addItemToDb(item: Result) {
        viewModelScope.launch {
            repository.insertSavedMovie(
                item

            )
        }
    }

}