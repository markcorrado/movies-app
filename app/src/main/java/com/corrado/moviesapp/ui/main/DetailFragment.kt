package com.corrado.moviesapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.ApiBuilder
import com.corrado.moviesapp.ui.main.api.ApiHelper
import com.corrado.moviesapp.ui.main.utils.Status
import com.corrado.moviesapp.ui.main.utils.ViewModelFactory

class DetailFragment : Fragment() {

    companion object {
        const val TAG = "DetailFragment"
    }

    private lateinit var viewModel: MainViewModel
    private val args by navArgs<DetailFragmentArgs>()

    private var titleTextView: TextView? = null
    private var bodyTextView: TextView? = null
    private var containerView: View? = null
    private var progressBar: ProgressBar? = null
    private var genresTextView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        titleTextView = view.findViewById(R.id.title_textView)
        bodyTextView = view.findViewById(R.id.body_textView)
        progressBar = view.findViewById(R.id.progress_bar)
        containerView = view.findViewById(R.id.container)
        genresTextView = view.findViewById(R.id.genres_textView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiBuilder.apiService))
        ).get(MainViewModel::class.java)

        loadMovie(movieId)
    }

    private fun loadMovie(id: Int) {
        viewModel.getMovie(id).observe(this.viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { movie ->
                        containerView?.visibility = View.VISIBLE
                        progressBar?.visibility = View.GONE
                        titleTextView?.text = movie.title
                        bodyTextView?.text = movie.overview
                        //Getting just the name of the genre from genre list and separating with commas.
                        genresTextView?.text = movie.genres?.map { it.name }?.joinToString(separator = ", ")
                    }
                }
                Status.ERROR -> {
                    Log.e(TAG, "Failed to load movie")
                    progressBar?.visibility = View.GONE
                    Toast.makeText(context, "There was an error loading your movie. Try again later.", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    progressBar?.visibility = View.VISIBLE
                }
            }

        })
    }
}