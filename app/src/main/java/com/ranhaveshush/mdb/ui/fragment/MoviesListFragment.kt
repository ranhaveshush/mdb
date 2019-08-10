package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MovieListViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MoviesListViewModel
import com.ranhaveshush.mdb.vo.MovieItem
import kotlinx.android.synthetic.main.fragment_movies_list.recyclerView_movies

abstract class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    protected val viewModel: MoviesListViewModel by viewModels(
        factoryProducer = { MovieListViewModelFactory() }
    )

    private val moviesAdapter: MoviesAdapter = MoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_movies.adapter = moviesAdapter

        getMoviesList().observe(this, Observer {
            moviesAdapter.submitList(it)
        })
    }

    protected abstract fun getMoviesList(): LiveData<PagedList<MovieItem>>
}
