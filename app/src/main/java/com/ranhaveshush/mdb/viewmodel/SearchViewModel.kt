package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.SearchRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 20

private const val EMPTY_STRING: String = ""

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    val query = MutableLiveData<String>()

    val movies = query.switchMap {
        if (it.isNullOrEmpty()) {
            repository.getPopular().toLiveData(PAGE_SIZE)
        } else {
            val query = it.toString()
            repository.search(query).toLiveData(PAGE_SIZE)
        }
    }

    fun getPosterUrl(movieItem: MovieItem): String = repository.getPosterUrl(movieItem)

    fun clearQuery() {
        query.value = EMPTY_STRING
    }
}
