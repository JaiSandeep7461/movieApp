package com.example.movieapp.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.models.Result
import com.example.movieapp.data.models.TopRatedResult
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieResponse = MutableStateFlow<Resource<PagingData<Item>>>(Resource.Loading())
    val movieResponse: MutableStateFlow<Resource<PagingData<Item>>> = _movieResponse

    private val _nowPlayingResponse = MutableStateFlow<Resource<PagingData<Result>>>(Resource.Loading())
    val nowPlayingResponse:MutableStateFlow<Resource<PagingData<Result>>> = _nowPlayingResponse


    private val _topRatedResponse = MutableStateFlow<Resource<PagingData<TopRatedResult>>>(Resource.Loading())
    val topRatedResponse : MutableStateFlow<Resource<PagingData<TopRatedResult>>> = _topRatedResponse

    init {
        Log.e("13072024","This is Now Playing")
        getMovieList()
    }

    private fun getMovieList() {
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

    private fun getNowPlayingList(){
        viewModelScope.launch {
            try {
                repository.getNowPlaying().cachedIn(viewModelScope).collect{pagingData->
                    _nowPlayingResponse.value = Resource.Success(pagingData)
                }
            }catch (e:Exception){
                _nowPlayingResponse.value = Resource.Error(e)
            }
        }
    }

    private fun getTopRatedList(){
        viewModelScope.launch {
            try {
                repository.getTopRatedPlaying().cachedIn(viewModelScope).collect{pagingData->
                    _topRatedResponse.value = Resource.Success(pagingData)
                }
            }catch (e:Exception){
                _topRatedResponse.value = Resource.Error(e)
            }
        }
    }
}