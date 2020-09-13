package com.corrado.moviesapp.ui.main.api

import com.corrado.moviesapp.BuildConfig

class ApiHelper(private val apiService: ApiService) {
    //TODO: hardcoding page for now
    suspend fun getPopularMovies() = apiService.getPopularMovies(BuildConfig.API_KEY, 1)

    suspend fun getMovie(id: Int) = apiService.getMovie(id, BuildConfig.API_KEY)

    suspend fun getConfig() = apiService.getConfig(BuildConfig.API_KEY)
}