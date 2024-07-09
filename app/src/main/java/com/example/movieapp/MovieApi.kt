package com.example.movieapp

import com.example.movieapp.data.models.ItemResponse
import com.example.movieapp.data.models.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?language=en-US")
    suspend fun getMovies(
        @Query("page") page:Int
    ):ItemResponse

    @GET("movie/now_playing?language=en-US")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ):NowPlayingResponse

}