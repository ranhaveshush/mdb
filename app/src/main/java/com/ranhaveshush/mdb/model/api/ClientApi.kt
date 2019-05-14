package com.ranhaveshush.mdb.model.api

/**
 * The client API interface, implemented by the specific API providers.
 */
interface ClientApi {
    fun getMovieService(): MovieService
}
