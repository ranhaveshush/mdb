package com.ranhaveshush.mdb.api

/**
 * Represents a function.
 *
 * @param <I> the type of the input to the function
 * @param <O> the type of the output of the function
</O></I> */
interface Function<I, O> {
    /**
     * Applies this function to the given input.
     *
     * @param input the input
     * @return the function result.
     */
    fun apply(input: I): O
}