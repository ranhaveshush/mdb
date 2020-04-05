package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {
    fun getDetails(movieId: Int): LiveData<Resource<MovieDetails>> = liveData {
        emitSource(repository.getDetails(movieId).asLiveData(Dispatchers.IO))
    }
}
