package com.corrado.moviesapp.ui.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.model.Movie
import java.text.SimpleDateFormat
import java.util.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var movie: Movie? = null

    private val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
    private val authorTextView: TextView = itemView.findViewById(R.id.author_textView)
    private val summaryTextView: TextView = itemView.findViewById(R.id.summary_textView)
    private val dateTextView: TextView = itemView.findViewById(R.id.date_textView)

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(movie: Movie) {
        this.movie = movie
        titleTextView.text = movie.title
    }

    override fun onClick(v: View) {
//        val context = v.context
//        context.startActivity(PostActivity.newIntent(v.context, postMetadata!!.postId!!))
    }
}