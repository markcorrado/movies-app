package com.corrado.moviesapp.ui.main.api.model

class ConfigModel {
    var images: ImageData? = null
}

class ImageData {
    var base_url: String? = null
    var poster_sizes: List<String>? = null
}