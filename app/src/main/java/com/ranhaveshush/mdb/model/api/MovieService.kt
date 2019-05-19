package com.ranhaveshush.mdb.model.api

import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.Deferred

/**
 * The movie service interface, implemented by the specific movie services.
 */
interface MovieService {
    /**
     * Search for movies by a given query string.
     *
     * @param query The query string.
     * @return A list of movie details returned by the search result.
     */
    fun search(query: String): Deferred<List<Movie>>

    /**
     * Retrieve movie details for a given movie ID.
     *
     * @param movieId The movie identifier.
     * @return The movie details.
     */
    fun getDetails(movieId: Int): Deferred<Movie>
}
