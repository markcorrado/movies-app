package com.corrado.moviesapp.ui.main.api

import com.corrado.moviesapp.ui.main.api.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int,
                         @Query("api_key") apiKey: String,
                         @Query("language") language: String
    ): Movie
}