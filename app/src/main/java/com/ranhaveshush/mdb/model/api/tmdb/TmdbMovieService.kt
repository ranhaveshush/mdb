package com.ranhaveshush.mdb.model.api.tmdb

import com.ranhaveshush.mdb.model.vo.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * A TMDb [MovieService][com.ranhaveshush.mdb.model.api.MovieService] implementation.
 */
interface TmdbMovieService {
    @GET("search/movie")
    fun search(@Query("query") query: String): Deferred<List<Movie>>

    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") movieId: Int): Deferred<Movie>
}
