package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.model.api.ApiFactory
import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.vo.Movie
import com.ranhaveshush.mdb.model.repository.MovieDetailsRepository
import kotlinx.coroutines.launch

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MovieDetailsViewModel : ViewModel() {
    private val client = ApiFactory.get(ApiProvider.TMDb)
    private val repository = MovieDetailsRepository(client)

    private val movieLiveData = MutableLiveData<Movie>()

    fun getDetails(movieId: Int): LiveData<Movie> {
        viewModelScope.launch {
            val movie = repository.getDetails(movieId).await()
            movieLiveData.postValue(movie)
        }

        return movieLiveData
    }
}
