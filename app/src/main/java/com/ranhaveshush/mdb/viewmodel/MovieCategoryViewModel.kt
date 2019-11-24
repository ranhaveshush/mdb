package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
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
    fun getMovies(category: Category): LiveData<PagedList<MovieItem>> =
        liveData {
            val dataSource = when (category) {
                Category.POPULAR -> repository.getPopular()
                Category.TOP_RATED -> repository.getTopRated()
                Category.UPCOMING -> repository.getUpcoming()
            }

            emitSource(dataSource.toLiveData(PAGE_SIZE))
        }

    fun getPosterUrl(movieItem: MovieItem) = repository.getPosterUrl(movieItem)

    /**
     * A singleton object for creating MoviesListViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() = ViewModelFactoryProducer.of(
            MovieCategoryViewModel::class.java,
            MovieCategoryRepository::class.java
        )
    }
}
