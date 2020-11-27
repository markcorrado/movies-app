package com.corrado.moviesapp.ui.main.api.model

import com.google.gson.annotations.SerializedName

class MovieModel {
    var id: Int = 0
    var title: String = ""
    var overview: String = ""
    @SerializedName("poster_path")
    var posterPath: String = ""
    @SerializedName("backdrop_path")
    var backdropPath: String = ""
    var genres: List<GenreModel>? = null
}