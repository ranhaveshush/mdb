package com.ranhaveshush.mdb.view.fragment

import androidx.navigation.fragment.findNavController

class TopRatedMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getTopRated(page)

    override fun presentMovieDetails(movieId: Int, movieTitle: String) {
        val action = TopRatedMoviesFragmentDirections.presentMovieDetailsAction(movieId, movieTitle)
        findNavController().navigate(action)
    }
}
