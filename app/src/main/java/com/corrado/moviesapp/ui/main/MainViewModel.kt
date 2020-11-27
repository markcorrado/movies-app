package com.corrado.moviesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.corrado.moviesapp.ui.main.api.Repository
import com.corrado.moviesapp.ui.main.api.model.ConfigModel
import com.corrado.moviesapp.ui.main.api.model.MovieModel
import com.corrado.moviesapp.ui.main.utils.Result
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val repository: Repository) : ViewModel() {

    var configModel: ConfigModel? = null
    private val _movieModel = MutableLiveData<MovieModel>(null)
    val movieModel: LiveData<MovieModel> = _movieModel

    fun onMovieModelChanged(newMovieModel: MovieModel) {
        _movieModel.value = newMovieModel
    }

    fun getMovie(id: Int) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(data = repository.getMovie(id)))
        } catch (exception: Exception) {
            emit(Result.error(data = null, message = exception.message ?: "Error getting Movie!"))
        }
    }

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(data = repository.getPopularMovies()))
        } catch (exception: Exception) {
            emit(Result.error(data = null, message = exception.message ?: "Error getting Popular Movies!"))
        }
    }

    fun getConfig() = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            configModel = repository.getConfig()
            emit(Result.success(data = configModel))
        } catch (exception: Exception) {
            emit(Result.error(data = null, message = exception.message ?: "Error getting Config!"))
        }
    }
}