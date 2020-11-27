package com.corrado.moviesapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.corrado.moviesapp.ui.main.api.ApiBuilder
import com.corrado.moviesapp.ui.main.api.ApiHelper
import com.corrado.moviesapp.ui.main.api.model.MovieModel
import com.corrado.moviesapp.ui.main.utils.Status
import com.corrado.moviesapp.ui.main.utils.ViewModelFactory

class DetailFragment : Fragment() {

    companion object {
        const val TAG = "DetailFragment"
    }

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewModel: MainViewModel = viewModel("vm", ViewModelFactory(ApiHelper(ApiBuilder.apiService)))
                val state = MutableLiveData(Status.LOADING)

                @Composable
                fun DetailScreen() {
                    val movieModel = viewModel.movieModel.observeAsState()
                    movieModel.value.let {
                        Column {
                            if (it != null) {
                                Text(text = it.title)
                                Text(text = it.overview)
                                Text(text = it.genres?.map { it.name }?.joinToString(separator = ", ")!!)
                            }
                        }
                    }
                }

                @Composable
                fun LoadingScreen() {
                    val myState = state.observeAsState(Status.LOADING)
                    if (myState.value == Status.LOADING) {
                        CircularProgressIndicator()
                    }
                }

                fun loadMovie(id: Int) {
                    viewModel.getMovie(id).observe(viewLifecycleOwner, { result ->
                        state.postValue(result.status)
                        when (result.status) {
                            Status.SUCCESS -> {
                                result.data?.let { movie ->
                                    viewModel.onMovieModelChanged(movie)
                                }
                            }
                            Status.ERROR -> {
                                Log.e(TAG, "Failed to load movie")
                                Toast.makeText(context, "There was an error loading your movie. Try again later.", Toast.LENGTH_SHORT).show()
                            }
                            Status.LOADING -> {}
                        }
                    })
                }

                MaterialTheme {
                    LoadingScreen()
                    loadMovie(args.movieId)
                    DetailScreen()
                }
            }
        }
    }

    private fun updateUIWithMovie(movieModel: MovieModel) {
//        containerView?.visibility = View.VISIBLE
//        progressBar?.visibility = View.GONE
//        titleTextView?.text = movieModel.title
//        bodyTextView?.text = movieModel.overview
//        //Getting just the name of the genre from genre list and separating with commas.
//        genresTextView?.text = movieModel.genres?.map { it.name }?.joinToString(separator = ", ")
//        val stringBuilder = StringBuilder()
//        viewModel.configModel?.let { config ->
//            stringBuilder.append(config.images?.base_url)
//            stringBuilder.append(config.images?.poster_sizes?.get(3))
//            stringBuilder.append(movieModel.backdropPath)
//        }
//        Picasso
//            .get()
//            .load(stringBuilder.toString())
//            .fit()
//            .centerInside()
//            .into(moviesImageView)
    }
}