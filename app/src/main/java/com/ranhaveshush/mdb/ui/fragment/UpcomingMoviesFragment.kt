package com.ranhaveshush.mdb.ui.fragment

class UpcomingMoviesFragment : MoviesListFragment() {
    override val moviesList = viewModel.upcomingMovies
}
