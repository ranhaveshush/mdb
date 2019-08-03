package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.MoviesRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 50

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun search(query: String): LiveData<PagedList<MovieItem>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.search(query).toLiveData(PAGE_SIZE))
    }

    fun getPopular(): LiveData<PagedList<MovieItem>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    fun getTopRated(): LiveData<PagedList<MovieItem>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getTopRated().toLiveData(PAGE_SIZE))
    }

    fun getUpcoming(): LiveData<PagedList<MovieItem>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getUpcoming().toLiveData(PAGE_SIZE))
    }
}
