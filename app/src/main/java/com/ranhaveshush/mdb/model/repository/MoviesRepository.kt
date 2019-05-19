package com.ranhaveshush.mdb.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import com.ranhaveshush.mdb.model.vo.Resource
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
    private val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()

    fun search(query: String): LiveData<Resource<List<Movie>>> {
        moviesLiveData.value = Resource.loading()

        coroutineScope.launch {
            try {
                val movies = client.getMovieService().search(query).await()
                moviesLiveData.postValue(Resource.success(movies))
            } catch (e: Exception) {
                moviesLiveData.postValue(Resource.error(e.message))
            }
        }

        return moviesLiveData
    }
}
