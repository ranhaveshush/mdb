package com.ranhaveshush.mdb.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * The movies repository.
 * An abstraction layer between the movies data sources and the app.
 */
class MoviesRepository(
    private val coroutineScope: CoroutineScope,
    private val client: ClientApi
) {
    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun search(query: String): LiveData<List<Movie>> {
        coroutineScope.launch {
            val movies = client.getMovieService().search(query).await()
            moviesLiveData.postValue(movies)
        }

        return moviesLiveData
    }
}
