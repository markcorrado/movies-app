package com.corrado.moviesapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.Config
import com.corrado.moviesapp.ui.main.api.model.Movie

class MovieAdapter(
    private var movieList: List<Movie>,
    private var config: Config,
    private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view, config)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }
}