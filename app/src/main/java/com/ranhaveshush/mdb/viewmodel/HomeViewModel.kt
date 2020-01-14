package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.HomeRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem
import com.ranhaveshush.mdb.vo.Resource
import com.ranhaveshush.mdb.vo.State
import kotlinx.coroutines.Dispatchers

private const val FEATURED_MOVIE_ID: Int = 475557

private const val PAGE_SIZE: Int = 10

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    val featuredMovie = getMovieDetails(FEATURED_MOVIE_ID).map {
        when (it.state) {
            State.SUCCESS -> it.data
            else -> null
        }
    }

    val popularMovies = liveData {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    val topRatedMovies = liveData {
        emitSource(repository.getTopRated().toLiveData(PAGE_SIZE))
    }

    val upcomingMovies = liveData {
        emitSource(repository.getUpcoming().toLiveData(PAGE_SIZE))
    }

    fun getPosterUrl(movieItem: MovieItem) = repository.getPosterUrl(movieItem)

    fun getBackdropUrl(movieDetails: MovieDetails) = repository.getBackdropUrl(movieDetails)

    private fun getMovieDetails(movieId: Int): LiveData<Resource<MovieDetails>> = liveData {
        emitSource(repository.getDetails(movieId).asLiveData(Dispatchers.IO))
    }

    /**
     * A singleton object for creating HomeViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() =
            ViewModelFactoryProducer.of(HomeViewModel::class.java, HomeRepository::class.java)
    }
}
