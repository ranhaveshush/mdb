package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import javax.inject.Inject

/**
 * A factory class for creating [MovieDetailsViewModel]s.
 */
class MovieDetailsViewModelFactory @Inject constructor(
    private val repository: MovieDetailsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MovieDetailsViewModel(repository) as T
    }
}
