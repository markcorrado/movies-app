package com.corrado.moviesapp.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.Config
import com.corrado.moviesapp.ui.main.api.model.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View, private val config: Config) : RecyclerView.ViewHolder(itemView) {
    var movie: Movie? = null

    private val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.movie_poster_imageView)
//    private val authorTextView: TextView = itemView.findViewById(R.id.author_textView)
//    private val summaryTextView: TextView = itemView.findViewById(R.id.summary_textView)
//    private val dateTextView: TextView = itemView.findViewById(R.id.date_textView)

    fun bind(movie: Movie) {
        this.movie = movie
        titleTextView.text = movie.title
        val stringBuilder = StringBuilder()
        stringBuilder.append(config.images?.base_url)
        stringBuilder.append(config.images?.poster_sizes?.get(3))
        stringBuilder.append(movie.poster_path)
        
        Picasso.get().isLoggingEnabled = true
        Picasso
            .get()
            .load(stringBuilder.toString())
            .fit()
            .centerCrop()
            .into(moviePosterImageView)
    }
}