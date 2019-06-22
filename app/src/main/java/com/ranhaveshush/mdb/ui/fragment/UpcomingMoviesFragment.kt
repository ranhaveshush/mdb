package com.ranhaveshush.mdb.ui.fragment

class UpcomingMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getUpcoming(page)
}
