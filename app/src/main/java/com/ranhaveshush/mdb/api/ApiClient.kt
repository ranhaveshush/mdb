package com.ranhaveshush.mdb.api

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem

interface ApiClient {
    fun search(query: String): DataSource.Factory<Int, MovieItem>

    fun getPopular(): DataSource.Factory<Int, MovieItem>

    fun getTopRated(): DataSource.Factory<Int, MovieItem>

    fun getUpcoming(): DataSource.Factory<Int, MovieItem>

    @WorkerThread
    fun getDetails(movieId: Int): LiveData<MovieDetails>
}
