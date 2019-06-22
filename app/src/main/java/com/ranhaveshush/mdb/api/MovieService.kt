package com.ranhaveshush.mdb.api

import kotlinx.coroutines.Deferred

private const val DEFAULT_PAGE: Int = 1
private const val DEFAULT_REGION: String = "US"

/**
 * The movie service interface, implemented by the specific movie services.
 */
interface MovieService {
    /**
     * Search for movies by a given query string.
     *
     * @param query The query string.
     * @param page The results page number to retrieve.
     * @return A list of movies returned by the search result.
     */
    suspend fun search(query: String, page: Int = DEFAULT_PAGE): Deferred<MoviesPageResponse>

    /**
     * Get a list of the current popular movies. This list updates daily.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of popular movies.
     */
    suspend fun getPopular(region: String = DEFAULT_REGION, page: Int = DEFAULT_PAGE): Deferred<MoviesPageResponse>

    /**
     * Get the top rated movies.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of top rated movies.
     */
    suspend fun getTopRated(region: String = DEFAULT_REGION, page: Int = DEFAULT_PAGE): Deferred<MoviesPageResponse>

    /**
     * Get the upcoming movies.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of upcoming movies.
     */
    suspend fun getUpcoming(region: String = DEFAULT_REGION, page: Int = DEFAULT_PAGE): Deferred<MoviesPageResponse>

    /**
     * Retrieve movie details for a given movie ID.
     *
     * @param movieId The movie identifier.
     * @param language The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @return The movie details.
     */
    suspend fun getDetails(movieId: Int, language: String = DEFAULT_REGION): Deferred<MovieDetailsResponse>
}
