package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.model.api.ApiFactory
import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.dto.MovieDTO
import com.ranhaveshush.mdb.model.repository.MoviesRepository
import kotlinx.coroutines.launch

/**
 * A movies [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MoviesViewModel : ViewModel() {
    private val client = ApiFactory.get(ApiProvider.TMDb)
    private val repository = MoviesRepository(client)

    private val moviesLiveData = MutableLiveData<List<MovieDTO>>()

    fun search(query: String): LiveData<List<MovieDTO>> {
        viewModelScope.launch {
            val movies = repository.search(query).await()
            moviesLiveData.postValue(movies)
        }

        return moviesLiveData
    }
}
