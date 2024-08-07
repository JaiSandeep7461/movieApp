package com.example.movieapp

import com.example.movieapp.data.models.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("movie/popular?language=en-US")
    suspend fun getMovies(
        @Query("page") page:Int
    ):ItemResponse
}