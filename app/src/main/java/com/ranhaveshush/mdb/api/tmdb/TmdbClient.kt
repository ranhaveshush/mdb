package com.ranhaveshush.mdb.api.tmdb

import androidx.paging.DataSource
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.tmdb.datasource.PopularMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.SearchMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.TmdbMovieDetailsToMovieDetailsFunction
import com.ranhaveshush.mdb.api.tmdb.datasource.TopRatedMoviesPagedDataSource
import com.ranhaveshush.mdb.api.tmdb.datasource.UpcomingMoviesPagedDataSource
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    override fun search(query: String): DataSource.Factory<Int, MovieItem> =
        SearchMoviesPagedDataSource.Factory(api, query)

    override fun getPopular(): DataSource.Factory<Int, MovieItem> =
        PopularMoviesPagedDataSource.Factory(api, locale)

    override fun getTopRated(): DataSource.Factory<Int, MovieItem> =
        TopRatedMoviesPagedDataSource.Factory(api, locale)

    override fun getUpcoming(): DataSource.Factory<Int, MovieItem> =
        UpcomingMoviesPagedDataSource.Factory(api, locale)

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        emit(Resource.loading())

        val resource = try {
            val response = api.service.getDetails(movieId, locale.country).execute()
            if (response.isSuccessful) {
                val tmdbMovieDetails = response.body()!!
                val movieDetails =
                    TmdbMovieDetailsToMovieDetailsFunction().apply(tmdbMovieDetails)
                Resource.success(movieDetails)
            } else {
                Resource.error(response.message())
            }
        } catch (e: Exception) {
            Resource.error<MovieDetails>(e.localizedMessage!!, e.cause)
        }

        emit(resource)
    }

    override fun getPosterUrl(movieItem: MovieItem): String =
        "${IMAGE_BASE_URL}${POSTER_SIZE}${movieItem.posterUrl}"

    override fun getBackdropUrl(movieDetails: MovieDetails): String =
        "${IMAGE_BASE_URL}${BACKDROP_SIZE}${movieDetails.backdropPath}"
}
