package com.ranhaveshush.mdb.api

import androidx.paging.DataSource
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.Resource
import kotlinx.coroutines.flow.Flow

/**
 * An API client interface that provides a contract between
 * the different [ApiClient] provider specific implementations.
 * This interface is used by [ClientFactory] a factory
 * for creating [ApiClient] provider implementations.
 */
interface ApiClient {
    fun search(query: String): DataSource.Factory<Int, MovieItem>

    fun getPopular(): DataSource.Factory<Int, MovieItem>

    fun getTopRated(): DataSource.Factory<Int, MovieItem>

    fun getUpcoming(): DataSource.Factory<Int, MovieItem>

    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>>

    fun getPosterUrl(movieItem: MovieItem): String

    fun getBackdropUrl(movieDetails: MovieDetails): String
}
