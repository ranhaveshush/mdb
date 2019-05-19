package com.ranhaveshush.mdb.model.repository

import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.Deferred

/**
 * The movie details repository.
 * An abstraction layer between the movie details data sources and the app.
 */
class MovieDetailsRepository(private val client: ClientApi) {
    fun getDetails(movieId: Int): Deferred<Movie> = client.getMovieService().getDetails(movieId)
}
