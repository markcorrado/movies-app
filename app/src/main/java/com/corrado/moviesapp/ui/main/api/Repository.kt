package com.corrado.moviesapp.ui.main.api

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getMovie(id: Int) = apiHelper.getMovie(id)
}