package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ranhaveshush.mdb.repository.MoviesRepository
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun search(query: String): LiveData<PagedList<MovieItem>> = repository.search(query)

    fun getPopular(): LiveData<PagedList<MovieItem>> = repository.getPopular()

    fun getTopRated(): LiveData<PagedList<MovieItem>> = repository.getTopRated()

    fun getUpcoming(): LiveData<PagedList<MovieItem>> = repository.getUpcoming()
}
