package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MovieListViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MoviesListViewModel
import com.ranhaveshush.mdb.vo.MoviesCategory
import kotlinx.android.synthetic.main.fragment_home.popular_movies
import kotlinx.android.synthetic.main.fragment_home.top_rated_movies
import kotlinx.android.synthetic.main.fragment_home.upcoming_movies

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: MoviesListViewModel by viewModels(
        factoryProducer = { MovieListViewModelFactory() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesCategories = createMoviesCategories()
        moviesCategories.forEach { (view, category) ->
            bindMoviesCategory(view, category)
        }
    }

    private fun createMoviesCategories(): Map<View, MoviesCategory> = mapOf(
        Pair(popular_movies, MoviesCategory(
            getString(R.string.fragment_popular_movies_title),
            { viewModel.getPopular() },
            { HomeFragmentDirections.actionHomeFragmentToPopularMoviesFragment() }
        )),
        Pair(top_rated_movies, MoviesCategory(
            getString(R.string.fragment_top_rated_movies_title),
            { viewModel.getTopRated() },
            { HomeFragmentDirections.actionHomeFragmentToTopRatedMoviesFragment() }
        )),
        Pair(upcoming_movies, MoviesCategory(
            getString(R.string.fragment_upcoming_movies_title),
            { viewModel.getUpcoming() },
            { HomeFragmentDirections.actionHomeFragmentToUpcomingMoviesFragment() }
        )))

    private fun bindMoviesCategory(
        view: View,
        category: MoviesCategory
    ) {
        val moviesListView = view as ViewGroup
        val moviesTitleTextView = moviesListView.findViewById<TextView>(R.id.textView_moviesTitle)
        val moviesRecyclerView = moviesListView.findViewById<RecyclerView>(R.id.recyclerView_movies)

        moviesTitleTextView.text = category.title
        moviesTitleTextView.setOnClickListener {
            findNavController().navigate(category.getDirectionsToCategory())
        }

        moviesRecyclerView.adapter = MoviesAdapter()
        category.getLiveMoviesList().observe(this@HomeFragment, Observer {
            val adapter = moviesRecyclerView.adapter as MoviesAdapter
            adapter.submitList(it)
        })
    }
}
