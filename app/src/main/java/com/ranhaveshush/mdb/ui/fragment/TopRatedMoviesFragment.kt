package com.ranhaveshush.mdb.ui.fragment

class TopRatedMoviesFragment : MoviesListFragment() {
    override val moviesList = viewModel.topRatedMovies
}
