package com.ranhaveshush.mdb.ui.fragment

class PopularMoviesFragment : MoviesListFragment() {
    override val moviesList = viewModel.popularMovies
}
