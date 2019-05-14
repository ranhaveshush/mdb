package com.ranhaveshush.mdb.model

import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.dto.MovieDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * The movie repository an abstraction between the movie data sources and the app.
 */
class MovieRepository(private val client: ClientApi) {
    private val defaultScope: CoroutineScope = DefaultScope()
    private val mainScope: CoroutineScope = MainScope()

    fun search(query: String, onResult: (isSuccess: Boolean, data: List<MovieDTO>?) -> Unit) {
        defaultScope.launch {
            try {
                val movies: List<MovieDTO> = client.getMovieService().search(query).await()
                mainScope.launch { onResult(true, movies) }
            } catch (e: IOException) {
                mainScope.launch { onResult(false, null) }
            }
        }
    }

    fun getDetails(movieId: Int, onResult: (isSuccess: Boolean, data: MovieDTO?) -> Unit) {
        defaultScope.launch {
            try {
                val movie: MovieDTO = client.getMovieService().getDetails(movieId).await()
                mainScope.launch { onResult(true, movie) }
            } catch (e: IOException) {
                mainScope.launch { onResult(false, null) }
            }
        }
    }

    @Suppress("FunctionName")
    private fun DefaultScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
}
