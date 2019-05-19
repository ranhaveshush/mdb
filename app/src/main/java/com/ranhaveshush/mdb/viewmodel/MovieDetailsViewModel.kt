package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.model.api.ApiFactory
import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.model.vo.Movie
import com.ranhaveshush.mdb.model.vo.Resource

/**
 * A movie details [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MovieDetailsViewModel : ViewModel() {
    private val client = ApiFactory.get(ApiProvider.TMDb)
    private val repository = MovieDetailsRepository(viewModelScope, client)

    fun getDetails(movieId: Int): LiveData<Resource<Movie>> = repository.getDetails(movieId)
}
