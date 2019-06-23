package com.ranhaveshush.mdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ranhaveshush.mdb.R
import com.ranhaveshush.mdb.ui.adapter.MoviesAdapter
import com.ranhaveshush.mdb.viewmodel.MoviesViewModel
import com.ranhaveshush.mdb.vo.MovieItem
import kotlinx.android.synthetic.main.fragment_movies_list.*

abstract class MoviesListFragment : Fragment() {
    protected val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter: MoviesAdapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_movies.adapter = moviesAdapter

        getMoviesList().observe(this, Observer {
            moviesAdapter.submitList(it)
        })
    }

    protected abstract fun getMoviesList(): LiveData<PagedList<MovieItem>>
}
