package com.ranhaveshush.mdb.vo

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.paging.PagedList

/**
 * The movie category [value object][ValueObject].
 *
 * Aggregates the movie category title, live movies list
 * and the navigation directions to the category screen.
 */
data class MoviesCategory(
    val title: String,
    val getLiveMoviesList: () -> LiveData<PagedList<MovieItem>>,
    val getDirectionsToCategory: () -> NavDirections
) : ValueObject
