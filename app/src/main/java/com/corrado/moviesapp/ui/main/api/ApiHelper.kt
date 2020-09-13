package com.corrado.moviesapp.ui.main.api

import com.corrado.moviesapp.BuildConfig

class ApiHelper(private val apiService: ApiService) {
    //TODO: hardcoding language for now
    suspend fun getMovie(id: Int) = apiService.getMovie(id, BuildConfig.API_KEY, "en-US")
}