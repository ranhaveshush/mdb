package com.ranhaveshush.mdb.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(
    private val coroutineScope: CoroutineScope,
    private val client: ClientApi
) {
    private val movieLiveData = MutableLiveData<Movie>()

    fun getDetails(movieId: Int): LiveData<Movie> {
        coroutineScope.launch {
            val movie = client.getMovieService().getDetails(movieId).await()
            movieLiveData.postValue(movie)
        }

        return movieLiveData
    }
}
