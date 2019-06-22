package com.ranhaveshush.mdb.ui.fragment

class PopularMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getPopular(page)
}
