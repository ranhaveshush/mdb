package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.model.api.ApiFactory
import com.ranhaveshush.mdb.model.api.ApiProvider
import com.ranhaveshush.mdb.model.repository.MoviesRepository
import com.ranhaveshush.mdb.model.vo.Movie
import com.ranhaveshush.mdb.model.vo.Resource

/**
 * A movies [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class MoviesViewModel : ViewModel() {
    private val client = ApiFactory.get(ApiProvider.TMDb)
    private val repository = MoviesRepository(viewModelScope, client)

    fun search(query: String): LiveData<Resource<List<Movie>>> = repository.search(query)
}
