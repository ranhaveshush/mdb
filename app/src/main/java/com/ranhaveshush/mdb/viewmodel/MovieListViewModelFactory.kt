package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.MoviesRepository

/**
 * A factory for creating [MoviesListViewModel] with a default [MoviesRepository].
 */
class MovieListViewModelFactory(
    private val repository: MoviesRepository = MoviesRepository(ClientFactory.get(ApiProvider.TMDb))
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            MoviesListViewModel::class.java.isAssignableFrom(modelClass) ->
                modelClass.getConstructor(MoviesRepository::class.java).newInstance(repository)
            else -> TODO("$modelClass is not supported, expecting ${MoviesListViewModel::class.java}.")
        }
    }
}
