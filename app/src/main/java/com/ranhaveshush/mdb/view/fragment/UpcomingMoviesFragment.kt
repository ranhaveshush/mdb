package com.ranhaveshush.mdb.view.fragment

import androidx.navigation.fragment.findNavController

class UpcomingMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getUpcoming(page)

    override fun presentMovieDetails(movieId: Int, movieTitle: String) {
        val action = UpcomingMoviesFragmentDirections.presentMovieDetailsAction(movieId, movieTitle)
        findNavController().navigate(action)
    }
}
