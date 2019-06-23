package com.ranhaveshush.mdb.ui.fragment

class PopularMoviesFragment : MoviesListFragment() {
    override fun getMoviesList() = viewModel.getPopular()
}
