package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.MoviesRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 20

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class SearchViewModel(private val repository: MoviesRepository) : ViewModel() {
    val poplarMovies = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    fun search(query: String): LiveData<PagedList<MovieItem>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.search(query).toLiveData(PAGE_SIZE))
    }
}
