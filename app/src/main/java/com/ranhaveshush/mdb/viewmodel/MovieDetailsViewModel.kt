package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.launch

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
@Suppress("TooGenericExceptionCaught")
class MovieDetailsViewModel : ViewModel() {
    private val repository = MovieDetailsRepository(ApiProvider.TMDb)

    private val movieLiveData = MutableLiveData<Resource<MovieDetails>>()

    fun getDetails(movieId: Int): LiveData<Resource<MovieDetails>> {
        movieLiveData.value = Resource.loading()

        viewModelScope.launch {
            try {
                val movieDetails = repository.getDetails(movieId)
                movieLiveData.postValue(Resource.success(movieDetails))
            } catch (e: Exception) {
                movieLiveData.postValue(Resource.error(e.message))
            }
        }

        return movieLiveData
    }
}
