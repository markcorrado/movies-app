package com.corrado.moviesapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.Movie

class MovieAdapter(var movieList: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val post = movieList[position]
        holder.bind(post)
    }
}