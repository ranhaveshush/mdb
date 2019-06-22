package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranhaveshush.mdb.api.ApiProvider
import com.ranhaveshush.mdb.repository.MoviesRepository
import com.ranhaveshush.mdb.vo.MoviesPage
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.launch

/**
 * A movies [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
@Suppress("TooGenericExceptionCaught")
class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository(ApiProvider.TMDb)

    private val moviesLiveData = MutableLiveData<Resource<MoviesPage>>()

    fun search(query: String, page: Int): LiveData<Resource<MoviesPage>> {
        moviesLiveData.value = Resource.loading()

        viewModelScope.launch {
            try {
                val moviesPage = repository.search(query, page)
                moviesLiveData.postValue(Resource.success(moviesPage))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(e.message))
            }
        }

        return moviesLiveData
    }

    fun getPopular(page: Int): LiveData<Resource<MoviesPage>> {
        moviesLiveData.value = Resource.loading()

        viewModelScope.launch {
            try {
                val moviesPage = repository.getPopular(page)
                moviesLiveData.postValue(Resource.success(moviesPage))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(e.message))
            }
        }

        return moviesLiveData
    }

    fun getTopRated(page: Int): LiveData<Resource<MoviesPage>> {
        moviesLiveData.value = Resource.loading()

        viewModelScope.launch {
            try {
                val moviesPage = repository.getTopRated(page)
                moviesLiveData.postValue(Resource.success(moviesPage))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(e.message))
            }
        }

        return moviesLiveData
    }

    fun getUpcoming(page: Int): LiveData<Resource<MoviesPage>> {
        moviesLiveData.value = Resource.loading()

        viewModelScope.launch {
            try {
                val moviesPage = repository.getUpcoming(page)
                moviesLiveData.postValue(Resource.success(moviesPage))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(e.message))
            }
        }

        return moviesLiveData
    }
}
