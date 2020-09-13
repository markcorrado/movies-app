package com.corrado.moviesapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.corrado.moviesapp.ui.main.api.Repository
import com.corrado.moviesapp.ui.main.utils.Result

import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun getMovie(id: Int) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(data = repository.getMovie(id)))
        } catch (exception: Exception) {
            emit(Result.error(data = null, message = exception.message ?: "Error getting Movie!"))
        }
    }
}