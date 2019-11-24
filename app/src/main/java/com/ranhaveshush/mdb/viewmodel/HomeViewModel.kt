package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.toLiveData
import com.ranhaveshush.mdb.repository.HomeRepository
import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem

private const val PAGE_SIZE: Int = 10

/**
 * A movies list [ViewModel] implementation.
 * An abstraction layer between the UI and the Model.
 */
class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    val popularMovies = liveData {
        emitSource(repository.getPopular().toLiveData(PAGE_SIZE))
    }

    val topRatedMovies = liveData {
        emitSource(repository.getTopRated().toLiveData(PAGE_SIZE))
    }

    val upcomingMovies = liveData {
        emitSource(repository.getUpcoming().toLiveData(PAGE_SIZE))
    }

    fun getMovieDetails(movieId: Int) = liveData {
        emit(repository.getDetails(movieId))
    }

    fun getPosterUrl(movieItem: MovieItem) = repository.getPosterUrl(movieItem)

    fun getBackdropUrl(movieDetails: MovieDetails) = repository.getBackdropUrl(movieDetails)

    /**
     * A singleton object for creating HomeViewModel [factory][androidx.lifecycle.ViewModelProvider.Factory].
     */
    object FactoryProducer {
        fun create() =
            ViewModelFactoryProducer.of(HomeViewModel::class.java, HomeRepository::class.java)
    }
}
