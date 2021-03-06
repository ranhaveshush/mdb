package com.ranhaveshush.mdb.api.tmdb

import com.ranhaveshush.mdb.api.tmdb.data.TmdbConfiguration
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieDetails
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMoviesPage
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * A TMDb movie service.
 */
interface TmdbMovieService {
    /**
     * Search for movies by a given query string.
     *
     * @param query The query string.
     * @param page The results page number to retrieve.
     * @return A list of movies returned by the search result.
     */
    @GET("search/movie")
    fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<TmdbMoviesPage>

    /**
     * Get a list of the current popular movies. This list updates daily.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of popular movies.
     */
    @GET("movie/popular")
    fun getPopular(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Call<TmdbMoviesPage>

    /**
     * Get the top rated movies.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of top rated movies.
     */
    @GET("movie/top_rated")
    fun getTopRated(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Call<TmdbMoviesPage>

    /**
     * Get the upcoming movies.
     *
     * @param region The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @param page The results page number to retrieve.
     * @return A list of upcoming movies.
     */
    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Call<TmdbMoviesPage>

    /**
     * Retrieve movie details for a given movie ID.
     *
     * @param movieId The movie identifier.
     * @param language The ISO 639-1 value to display translated data for the fields that support it. default en-US.
     * @return The movie details.
     */
    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<TmdbMovieDetails>

    /**
     * Retrieve the TMDb API system wide configuration information.
     * Some elements of the API require some knowledge of this configuration data.
     * This method currently holds the data relevant to building image URLs as well as the change key map.
     *
     * @return The TMDb API system wide configuration information.
     */
    @GET("configuration")
    fun getConfiguration(): Call<TmdbConfiguration>
}
