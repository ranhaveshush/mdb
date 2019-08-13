package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.Resource

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {
    fun getDetails(movieId: Int): LiveData<Resource<MovieDetails>> = liveData(viewModelScope.coroutineContext) {
        emitSource(repository.getDetails(movieId))
    }

    /**
     * A singleton object for creating MovieDetailsViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() =
            ViewModelFactoryProducer.of(MovieDetailsViewModel::class.java, MovieDetailsRepository::class.java)
    }
}
