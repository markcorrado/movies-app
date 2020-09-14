package com.corrado.moviesapp.ui.main.api

import com.corrado.moviesapp.ui.main.api.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("page") page: Int,
                         @Query("language") language: String? = "en_us"
    ): PopularMovieListModel

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int,
                         @Query("api_key") apiKey: String,
                         @Query("language") language: String? = "en_us"
    ): MovieModel

    @GET("configuration")
    suspend fun getConfig(@Query("api_key") apiKey: String): ConfigModel
}