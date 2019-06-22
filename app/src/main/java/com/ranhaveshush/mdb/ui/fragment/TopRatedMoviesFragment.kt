package com.ranhaveshush.mdb.ui.fragment

class TopRatedMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getTopRated(page)
}
