package com.ranhaveshush.mdb.ui.fragment

class UpcomingMoviesFragment : MoviesListFragment() {
    override fun getMoviesList() = viewModel.getUpcoming()
}
