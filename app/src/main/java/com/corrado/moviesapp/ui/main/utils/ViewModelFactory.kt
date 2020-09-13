package com.corrado.moviesapp.ui.main.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.corrado.moviesapp.ui.main.MainViewModel
import com.corrado.moviesapp.ui.main.api.ApiHelper
import com.corrado.moviesapp.ui.main.api.Repository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown view model class name")
    }
}