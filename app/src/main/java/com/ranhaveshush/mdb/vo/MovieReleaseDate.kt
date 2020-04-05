package com.ranhaveshush.mdb.vo

import java.util.Calendar

/**
 * The movie release date.
 */
data class MovieReleaseDate(private val releaseDate: Calendar) {
    override fun toString(): String = releaseDate.get(Calendar.YEAR).toString()
}