package com.corrado.moviesapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.ConfigModel
import com.corrado.moviesapp.ui.main.api.model.MovieModel

class MovieAdapter(
    private var movieModelList: List<MovieModel>,
    private var configModel: ConfigModel,
    private val listener: (MovieModel) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun getItemCount(): Int {
        return movieModelList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view, configModel)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieModelList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }
}