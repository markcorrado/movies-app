package com.corrado.moviesapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corrado.moviesapp.R
import com.corrado.moviesapp.ui.main.api.ApiBuilder
import com.corrado.moviesapp.ui.main.api.ApiHelper
import com.corrado.moviesapp.ui.main.utils.Status
import com.corrado.moviesapp.ui.main.utils.ViewModelFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MainFragment"
    }

    private lateinit var viewModel: MainViewModel

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progress_bar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiBuilder.apiService))
        ).get(MainViewModel::class.java)
        viewModel.getPopularMovies().observe(this, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { movieList ->
                            recyclerView?.adapter = movieList.results?.let { it1 -> MovieAdapter(it1) }
                            progressBar?.visibility = View.GONE
                            recyclerView?.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        Log.e(TAG, "Failed to load popular movies")
                        progressBar?.visibility = View.GONE
                        recyclerView?.visibility = View.VISIBLE
                        Toast.makeText(context, "There was an error loading movies. Try again later.", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        progressBar?.visibility = View.VISIBLE
                        recyclerView?.visibility = View.GONE
                    }
                }
            }
        })
    }
}