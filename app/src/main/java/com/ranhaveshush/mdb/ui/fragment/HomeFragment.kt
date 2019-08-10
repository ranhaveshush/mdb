package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MovieListViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MoviesListViewModel
import kotlinx.android.synthetic.main.fragment_home.popular_movies
import kotlinx.android.synthetic.main.fragment_home.top_rated_movies
import kotlinx.android.synthetic.main.fragment_home.upcoming_movies

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: MoviesListViewModel by viewModels(
        factoryProducer = { MovieListViewModelFactory() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMoviesCategory(
            popular_movies,
            getString(R.string.fragment_popular_movies_title),
            HomeFragmentDirections.actionHomeFragmentToPopularMoviesFragment()
        )
        setupMoviesCategory(
            top_rated_movies,
            getString(R.string.fragment_top_rated_movies_title),
            HomeFragmentDirections.actionHomeFragmentToTopRatedMoviesFragment()
        )
        setupMoviesCategory(
            upcoming_movies,
            getString(R.string.fragment_upcoming_movies_title),
            HomeFragmentDirections.actionHomeFragmentToUpcomingMoviesFragment()
        )
    }

    private fun setupMoviesCategory(
        view: View,
        title: String,
        action: NavDirections
    ) {
        val moviesCategoryView = view as ViewGroup
        val moviesCategoryTitleTextView = moviesCategoryView.findViewById<TextView>(R.id.textView_moviesTitle)
        val moviesCategoryRecyclerView = moviesCategoryView.findViewById<RecyclerView>(R.id.recyclerView_movies)

        moviesCategoryTitleTextView.text = title
        moviesCategoryTitleTextView.setOnClickListener { findNavController().navigate(action) }

        moviesCategoryRecyclerView.adapter = MoviesAdapter()
        viewModel.getPopular().observe(this@HomeFragment, Observer {
            val adapter = moviesCategoryRecyclerView.adapter as MoviesAdapter
            adapter.submitList(it)
        })
    }
}
