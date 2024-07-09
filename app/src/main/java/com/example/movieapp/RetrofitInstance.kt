package com.example.movieapp

import com.example.movieapp.utils.Constants.BASE_URL
import com.example.movieapp.utils.Constants.TOKEN
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    val api: MovieApi by lazy {

        val interceptor = HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .readTimeout(120L, TimeUnit.SECONDS)
            .connectTimeout(120L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization","Bearer $TOKEN")
                .build()
            chain.proceed(newRequest)
        }.build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}