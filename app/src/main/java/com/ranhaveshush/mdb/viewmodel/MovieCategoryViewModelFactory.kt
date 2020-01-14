package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.repository.MovieCategoryRepository

/**
 * A factory class for creating [MovieCategoryViewModel]s.
 */
class MovieCategoryViewModelFactory(
    private val repository: MovieCategoryRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MovieCategoryViewModel(repository) as T
    }
}
