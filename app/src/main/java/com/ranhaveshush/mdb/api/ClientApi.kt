package com.ranhaveshush.mdb.api

/**
 * The client API interface, implemented by the specific API providers.
 */
interface ClientApi {
    fun getMovieService(): MovieService

    fun getConverterFactory(): Converter.Factory
}
