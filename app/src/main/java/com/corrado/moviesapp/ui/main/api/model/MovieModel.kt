package com.corrado.moviesapp.ui.main.api.model

import com.google.gson.annotations.SerializedName

class MovieModel {
    var id: Int? = null
    var title: String? = null
    var overview: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    var genres: List<GenreModel>? = null
}