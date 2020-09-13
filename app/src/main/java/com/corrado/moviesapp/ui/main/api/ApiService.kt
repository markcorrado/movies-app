package com.corrado.moviesapp.ui.main.api

import com.corrado.moviesapp.ui.main.api.model.Config
import com.corrado.moviesapp.ui.main.api.model.Movie
import com.corrado.moviesapp.ui.main.api.model.PopularMovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("page") page: Int,
                         @Query("language") language: String? = "en_us"
    ): PopularMovieList

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int,
                         @Query("api_key") apiKey: String,
                         @Query("language") language: String? = "en_us"
    ): Movie

    @GET("configuration")
    suspend fun getConfig(@Query("api_key") apiKey: String): Config
}