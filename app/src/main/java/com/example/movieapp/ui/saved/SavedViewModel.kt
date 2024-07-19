package com.example.movieapp.ui.saved

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.BookmarkedMovie
import com.example.movieapp.data.models.Item
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    private val _savedDbResponse = MutableStateFlow<Resource<List<Item>>>(Resource.Loading())
     val savedDbResponse:MutableStateFlow<Resource<List<Item>>> = _savedDbResponse


    init {
        getSavedDatabaseResults()
    }

      fun getSavedDatabaseResults() {
        viewModelScope.launch {
//            _savedDbResponse.value = repository.getAllSavedQuotes()
            try {
                _savedDbResponse.value = Resource.Success(repository.getAllSavedQuotes())
            }catch (e:Exception){
                _savedDbResponse.value = Resource.Error(e)
            }

            Log.e("18072024","the response count >>> ${_savedDbResponse.value.data!!.size}")
        }
    }

    fun deleteItem(item: Item){
        Log.e("19072024","The Viewmodel>>> ${item.title}")
        viewModelScope.launch {
            repository.deleteSavedQuote(item.id.toString())
            _savedDbResponse.value = Resource.Success(repository.getAllSavedQuotes())
        }
    }



}