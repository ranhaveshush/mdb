package com.ranhaveshush.mdb.api.tmdb

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * A TMDb [MovieService][com.ranhaveshush.mdb.api.MovieService] implementation.
 */
interface TmdbMovieService {
    @GET("search/movie")
    fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Deferred<TmdbMoviesPageResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Deferred<TmdbMoviesPageResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Deferred<TmdbMoviesPageResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("region") region: String,
        @Query("page") page: Int
    ): Deferred<TmdbMoviesPageResponse>

    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Deferred<TmdbMovieDetailsResponse>
}
