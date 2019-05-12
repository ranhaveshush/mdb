package com.ranhaveshush.mdb.model.service

import com.ranhaveshush.mdb.model.service.tmdb.TmdbService
import com.ranhaveshush.mdb.model.service.tmdb.TmdbServiceAdapter

/**
 * The movie service factory.
 * A factory for movie services specific implementations.
 */
class MovieServiceFactory {
    companion object {
        fun get(provider: MovieServiceProvider): MovieService =
            when (provider) {
                MovieServiceProvider.TMDb -> TmdbServiceAdapter(TmdbService.get())
            }
    }
}

