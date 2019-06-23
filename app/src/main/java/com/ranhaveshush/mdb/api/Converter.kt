package com.ranhaveshush.mdb.api

import com.ranhaveshush.mdb.vo.MovieDetails
import com.ranhaveshush.mdb.vo.MovieItem

/**
 * A converter interface, implemented by concrete Api providers
 * to convert concrete Api responses to [value object][com.ranhaveshush.mdb.vo.ValueObject]'s.
 */
interface Converter<F, T> {
    fun convert(value: F): T

    interface Factory {
        fun movieItemConverter(): Converter<Any, MovieItem>

        fun movieDetailsConverter(): Converter<Any, MovieDetails>
    }
}
