package com.ranhaveshush.tmdb.model.service

import com.ranhaveshush.tmdb.model.dto.MovieDTO
import retrofit2.Call

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
    fun search(query: String): Call<List<MovieDTO>>

    /**
     * Retrieve movie details for a given movie ID.
     *
     * @param movieId The movie identifier.
     * @return The movie details.
     */
    fun getDetails(movieId: Int): Call<MovieDTO>
}
