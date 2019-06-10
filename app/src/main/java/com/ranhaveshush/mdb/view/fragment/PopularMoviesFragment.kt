package com.ranhaveshush.mdb.view.fragment

import androidx.navigation.fragment.findNavController

class PopularMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getPopular(page)

    override fun presentMovieDetails(movieId: Int, movieTitle: String) {
        val action = PopularMoviesFragmentDirections.presentMovieDetailsAction(movieId, movieTitle)
        findNavController().navigate(action)
    }
}
