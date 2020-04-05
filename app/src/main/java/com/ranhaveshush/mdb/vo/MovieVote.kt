package com.ranhaveshush.mdb.vo

/**
 * The movie vote average and count.
 */
data class MovieVote(private val voteAverage: Float, private val voteCount: Int) {
    private val voteMax: Int = 10

    override fun toString() = "${voteAverage}/${voteMax}"

    fun voteAverage(): String = voteAverage.toString()

    fun voteCount(): String = voteCount.toString()
}