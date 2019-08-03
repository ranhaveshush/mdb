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
import kotlinx.coroutines.Dispatchers
import java.util.*

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

    override suspend fun getDetails(movieId: Int): LiveData<MovieDetails> = liveData(Dispatchers.IO) {
        val response = api.service.getDetails(movieId, locale.country).execute()
        // TODO: 8/2/19 wrap TmdbMovieDetils with NetworkResource object to provide network state.
        val tmdbMovieDetails = response.body()!!
        val movieDetails = TmdbMovieDetailsToMovieDetailsFunction().apply(tmdbMovieDetails)
        emit(movieDetails)
    }
}
