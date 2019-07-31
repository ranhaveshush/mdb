package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.MovieDetailsRepository

/**
 * A factory for creating [MovieDetailsViewModel] with a default [MovieDetailsRepository].
 */
class MovieDetailsViewModelFactory(
    private val repository: MovieDetailsRepository = MovieDetailsRepository(ClientFactory.get(ApiProvider.TMDb))
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            MovieDetailsViewModel::class.java.isAssignableFrom(modelClass) ->
                modelClass.getConstructor(MovieDetailsRepository::class.java).newInstance(repository)
            else -> TODO("$modelClass is not supported, expecting ${MovieDetailsViewModel::class.java}.")
        }
    }
}
