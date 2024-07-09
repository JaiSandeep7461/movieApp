package com.example.movieapp.di

import com.example.movieapp.MovieApi
import com.example.movieapp.utils.Constants.BASE_URL
import com.example.movieapp.utils.Constants.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val AUTHORIZATION = "Authorization"
    private const val BEARER = "Bearer"
    private const val TIMEOUT = 120L

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor = Interceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader(AUTHORIZATION, "$BEARER $TOKEN").build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder().readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS).addInterceptor(logging)
            .addInterceptor(authInterceptor).build()

        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideMoviesAPi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

}