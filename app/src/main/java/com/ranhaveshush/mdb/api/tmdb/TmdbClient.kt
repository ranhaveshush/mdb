package com.ranhaveshush.mdb.api.tmdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
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
import kotlinx.coroutines.Dispatchers
import java.util.Locale

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
    override suspend fun getDetails(movieId: Int): LiveData<Resource<MovieDetails>> = liveData(Dispatchers.IO) {
        emit(Resource.loading<MovieDetails>())

        val resource: Resource<MovieDetails> = try {
            val response = api.service.getDetails(movieId, locale.country).execute()
            if (response.isSuccessful) {
                val tmdbMovieDetails = response.body()!!
                val movieDetails = TmdbMovieDetailsToMovieDetailsFunction().apply(tmdbMovieDetails)
                Resource.success(movieDetails)
            } else {
                Resource.error(response.message())
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage!!, e.cause)
        }

        emit(resource)
    }
}
