package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.MovieCategoryRepository
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 20

enum class Category {
    POPULAR,
    TOP_RATED,
    UPCOMING
}

/**
 * A movie category [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MovieCategoryViewModel(private val repository: MovieCategoryRepository) : ViewModel() {
    val category = MutableLiveData<Category>()

    val movies: LiveData<PagedList<MovieItem>> = category.switchMap {
        when (it) {
            Category.POPULAR -> repository.getPopular()
            Category.TOP_RATED -> repository.getTopRated()
            Category.UPCOMING -> repository.getUpcoming()
        }.toLiveData(PAGE_SIZE)
    }

    fun getPosterUrl(movieItem: MovieItem) = repository.getPosterUrl(movieItem)
}
