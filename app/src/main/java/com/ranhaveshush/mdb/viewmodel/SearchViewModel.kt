package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.SearchRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 20

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    val poplarMovies = liveData {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    fun search(query: String): LiveData<PagedList<MovieItem>> =
        liveData {
            emitSource(repository.search(query).toLiveData(PAGE_SIZE))
        }

    /**
     * A singleton object for creating SearchViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() =
            ViewModelFactoryProducer.of(SearchViewModel::class.java, SearchRepository::class.java)
    }
}
