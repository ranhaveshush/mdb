package com.ranhaveshush.mdb.vo

/**
 * The movie runtime.
 */
interface MovieRuntime {
    override fun toString(): String
    fun isEmpty(): Boolean

    companion object {
        fun create(timeInMinutes: Int?): MovieRuntime = if (timeInMinutes == null) {
            EmptyMovieRuntime()
        } else {
            NonEmptyMovieRuntime(timeInMinutes)
        }
    }
}

/**
 * An empty movie runtime.
 */
private class EmptyMovieRuntime() : MovieRuntime {
    override fun toString() = ""

    override fun isEmpty() = true
}

/**
 * A non empty movie runtime.
 */
private data class NonEmptyMovieRuntime(private val timeInMinutes: Int) : MovieRuntime {
    private val hours: Int = timeInMinutes / 60
    private val minutes: Int = timeInMinutes % 60

    override fun toString() = "${hours}h${minutes}m"

    override fun isEmpty() = false
}