package com.ranhaveshush.mdb.ui.fragment

class TopRatedMoviesFragment : MoviesListFragment() {
    override fun getMoviesList() = viewModel.getTopRated()
}
