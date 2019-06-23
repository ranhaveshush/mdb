package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import kotlinx.coroutines.launch

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
@Suppress("TooGenericExceptionCaught")
class MovieDetailsViewModel : ViewModel() {
    private val client = ClientFactory.get(ApiProvider.TMDb)
    private val repository = MovieDetailsRepository(client)

    private val movieDetailsLiveData = MutableLiveData<MovieDetails>()

    fun getDetails(movieId: Int): LiveData<MovieDetails> {
        viewModelScope.launch {
            try {
                val details = repository.getDetails(movieId)
                movieDetailsLiveData.postValue(details.value)
            } catch (e: Exception) {
                TODO("Implement error handling.")
            }
        }

        return movieDetailsLiveData
    }
}
