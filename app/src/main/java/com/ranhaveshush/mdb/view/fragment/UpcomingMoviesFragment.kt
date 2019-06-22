package com.ranhaveshush.mdb.view.fragment

class UpcomingMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getUpcoming(page)
}
