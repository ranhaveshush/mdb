package com.ranhaveshush.mdb.model.api

import com.ranhaveshush.mdb.model.vo.MovieDetails
import com.ranhaveshush.mdb.model.vo.MovieItem
import com.ranhaveshush.mdb.model.vo.MoviesPage

/**
 * A converter interface, implemented by concrete Api providers
 * to convert concrete Api responses to [value object][com.ranhaveshush.mdb.model.vo.ValueObject]'s.
 */
interface Converter<F, T> {
    fun convert(value: F): T

    interface Factory {
        fun moviesPageConverter(): Converter<Any, MoviesPage>

        fun movieItemConverter(): Converter<Any, MovieItem>

        fun movieDetailsConverter(): Converter<Any, MovieDetails>
    }
}
