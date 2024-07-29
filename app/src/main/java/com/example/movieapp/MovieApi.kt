package com.example.movieapp

import com.example.movieapp.data.models.popular.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?language=en-US")
    suspend fun getMovies(
        @Query("page") page:Int
    ): PopularResponse

   /* @GET("movie/now_playing?language=en-US")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): NowPlayingResponse*/

/*    @GET("movie/top_rated?language=en-US")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): TopRatedResponse*/

}