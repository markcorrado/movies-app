package com.corrado.moviesapp.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.ConfigModel
import com.corrado.moviesapp.ui.main.api.model.MovieModel
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View, private val configModel: ConfigModel) : RecyclerView.ViewHolder(itemView) {
    private var movieModel: MovieModel? = null

    private val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.movie_poster_imageView)

    fun bind(movieModel: MovieModel) {
        this.movieModel = movieModel
        titleTextView.text = movieModel.title
        val stringBuilder = StringBuilder()
        stringBuilder.append(configModel.images?.base_url)
        stringBuilder.append(configModel.images?.poster_sizes?.last())
        stringBuilder.append(movieModel.posterPath)
        Picasso
            .get()
            .load(stringBuilder.toString())
            .fit()
            .centerCrop()
            .into(moviePosterImageView)
    }
}