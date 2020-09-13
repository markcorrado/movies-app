package com.corrado.moviesapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiBuilder.apiService))
        ).get(MainViewModel::class.java)
        viewModel.getMovie(11).observe(this, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Log.e(TAG, "Success")

                        result.data?.let { postMetadataList ->
//                            postRecyclerView?.adapter = PostAdapter(postMetadataList)
//                            progressBar?.visibility = View.GONE
//                            postRecyclerViewlerView?.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR -> {
                        Log.e(TAG, "Failed to load postMetadata")
//                        progressBar?.visibility = View.GONE
//                        postRecyclerView?.visibility = View.VISIBLE
//                        Toast.makeText(this, "There was an error loading blog posts. Try again later.", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        Log.e(TAG, "Loading")
//                        progressBar?.visibility = View.VISIBLE
//                        postRecyclerView?.visibility = View.GONE
                    }
                }
            }
        })
    }
}