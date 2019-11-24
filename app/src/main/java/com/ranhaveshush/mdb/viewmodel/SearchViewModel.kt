package com.ranhaveshush.mdb.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.SearchRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 20

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    val query = MutableLiveData<Editable?>()

    val movies = query.switchMap {
        if (it.isNullOrEmpty()) {
            repository.getPopular().toLiveData(PAGE_SIZE)
        } else {
            val query = it.toString()
            repository.search(query).toLiveData(PAGE_SIZE)
        }
    }

    fun getPosterUrl(movieItem: MovieItem): String = repository.getPosterUrl(movieItem)

    /**
     * A singleton object for creating SearchViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() =
            ViewModelFactoryProducer.of(SearchViewModel::class.java, SearchRepository::class.java)
    }
}
