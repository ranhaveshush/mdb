package com.ranhaveshush.mdb.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import com.ranhaveshush.mdb.model.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(
    private val coroutineScope: CoroutineScope,
    private val client: ClientApi
) {
    private val movieLiveData = MutableLiveData<Resource<Movie>>()

    fun getDetails(movieId: Int): LiveData<Resource<Movie>> {
        movieLiveData.value = Resource.loading()

        coroutineScope.launch {
            try {
                val movie = client.getMovieService().getDetails(movieId).await()
                movieLiveData.postValue(Resource.success(movie))
            } catch (e: Exception) {
                movieLiveData.postValue(Resource.error(e.message))
            }
        }

        return movieLiveData
    }
}
