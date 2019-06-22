package com.ranhaveshush.mdb.view.fragment

class PopularMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getPopular(page)
}
