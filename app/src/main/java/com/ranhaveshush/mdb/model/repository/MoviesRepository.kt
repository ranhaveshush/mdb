package com.ranhaveshush.mdb.model.repository

import com.ranhaveshush.mdb.model.api.ClientApi
import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.Deferred

/**
 * The movies repository.
 * An abstraction layer between the movies data sources and the app.
 */
class MoviesRepository(private val client: ClientApi) {
    fun search(query: String): Deferred<List<Movie>> = client.getMovieService().search(query)
}
