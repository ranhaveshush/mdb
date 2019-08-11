package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.MoviesRepository

private const val PAGE_SIZE: Int = 10

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {
    val popularMovies = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    val topRatedMovies = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getTopRated().toLiveData(PAGE_SIZE))
    }

    val upcomingMovies = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getUpcoming().toLiveData(PAGE_SIZE))
    }
}
