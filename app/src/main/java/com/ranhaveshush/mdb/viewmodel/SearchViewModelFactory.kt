package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.MoviesRepository

/**
 * A factory for creating [SearchViewModel] with a default [MoviesRepository].
 */
class SearchViewModelFactory(
    private val repository: MoviesRepository = MoviesRepository(ClientFactory.get(ApiProvider.TMDb))
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            SearchViewModel::class.java.isAssignableFrom(modelClass) ->
                modelClass.getConstructor(MoviesRepository::class.java).newInstance(repository)
            else -> TODO("$modelClass is not supported, expecting ${SearchViewModel::class.java}.")
        }
    }
}
