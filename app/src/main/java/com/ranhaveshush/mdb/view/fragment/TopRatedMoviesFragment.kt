package com.ranhaveshush.mdb.view.fragment

class TopRatedMoviesFragment : MoviesListFragment() {
    override fun getMoviesList(page: Int) = viewModel.getTopRated(page)
}
