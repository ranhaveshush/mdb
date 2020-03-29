package com.ranhaveshush.mdb.api

import androidx.arch.core.util.Function
import retrofit2.Response

fun <T, R> Response<T>.adapt(function: Function<T, R>): Response<R> {
    return if (isSuccessful) {
        val data: T? = body()
        if (data != null) {
            val newData = data.let { function.apply(it) }
            Response.success(code(), newData)
        } else {
            Response.success<R>(code(), null)
        }
    } else {
        Response.error(code(), errorBody()!!)
    }
}
