package com.ranhaveshush.mdb.api

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            val code = response.code()
            val message = response.message()

            return if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    SuccessApiResponse(code, message, data)
                } else {
                    EmptyApiResponse(code, message)
                }
            } else {
                ErrorApiResponse(code, message)
            }
        }
    }
}

data class SuccessApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T
) : ApiResponse<T>()

data class EmptyApiResponse<T>(
    val code: Int,
    val message: String
) : ApiResponse<T>()

data class ErrorApiResponse<T>(
    val code: Int,
    val message: String,
    val cause: Throwable? = null
) : ApiResponse<T>()
