package com.ranhaveshush.mdb.api.tmdb

import androidx.paging.DataSource
import androidx.arch.core.util.Function
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.ApiResponse
import com.ranhaveshush.mdb.api.adapt
import com.ranhaveshush.mdb.api.tmdb.data.TmdbMovieItem
import com.ranhaveshush.mdb.api.tmdb.datasource.PopularMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.SearchMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.TmdbMovieDetailsToMovieDetailsFunction
import com.ranhaveshush.mdb.api.tmdb.datasource.TmdbMovieItemToMovieItemFunction
import com.ranhaveshush.mdb.api.tmdb.datasource.TopRatedMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.UpcomingMoviesPagedDataSource
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import java.util.Locale

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

private const val POSTER_SIZE = "w154"
private const val BACKDROP_SIZE = "w780"

/**
 * A TMDb [ApiClient] implementation.
 */
class TmdbClient(
    private val api: TmdbApi,
    private val locale: Locale = Locale.getDefault()
) : ApiClient {
    private val adapter = Function<TmdbMovieItem, MovieItem> { movieItem ->
        movieItem.apply {
            posterUrl = getPosterUrl(movieItem.posterPath)
        }
        TmdbMovieItemToMovieItemFunction().apply(movieItem)
    }

    override fun search(query: String): DataSource.Factory<Int, MovieItem> =
        SearchMoviesPagedDataSource.Factory(api, query, adapter)

    override fun getPopular(): DataSource.Factory<Int, MovieItem> =
        PopularMoviesPagedDataSource.Factory(api, locale, adapter)

    override fun getTopRated(): DataSource.Factory<Int, MovieItem> =
        TopRatedMoviesPagedDataSource.Factory(api, locale, adapter)

    override fun getUpcoming(): DataSource.Factory<Int, MovieItem> =
        UpcomingMoviesPagedDataSource.Factory(api, locale, adapter)

    override suspend fun getDetails(movieId: Int): ApiResponse<MovieDetails> {
        val response = api.service.getDetails(movieId, locale.language)

        val tmdbMovieDetails = response.body()
        tmdbMovieDetails?.apply {
            backdropUrl = getBackdropUrl(tmdbMovieDetails.backdropPath)
        }

        val adaptedResponse = response.adapt(TmdbMovieDetailsToMovieDetailsFunction())
        return ApiResponse.create(adaptedResponse)
    }

    private fun getPosterUrl(posterPath: String): String =
        "${IMAGE_BASE_URL}${POSTER_SIZE}${posterPath}"

    private fun getBackdropUrl(backdropPath: String): String =
        "${IMAGE_BASE_URL}${BACKDROP_SIZE}${backdropPath}"
}
