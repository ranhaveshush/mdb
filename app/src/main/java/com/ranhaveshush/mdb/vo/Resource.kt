package com.ranhaveshush.mdb.vo

/**
 * Resource current status.
 */
enum class Status {
    LOADING,
    SUCCESS,
    FAILURE
}

/**
 * The resource state comprised of the resource current [Status]
 * and in case of an error the error message and the error [cause][Throwable].
 */
data class ResourceState private constructor(
    private val status: Status,
    private val message: String? = null,
    private val cause: Throwable? = null
) {
    companion object {
        val LOADING = ResourceState(Status.LOADING)
        val SUCCESS = ResourceState(Status.SUCCESS)
        fun error(message: String, cause: Throwable? = null) =
            ResourceState(
                Status.FAILURE,
                message,
                cause
            )
    }
}

/**
 * The resource is comprised of the [resource state][ResourceState],
 * and the resource data of type [T].
 */
data class Resource<T> private constructor(
    val state: ResourceState,
    val data: T? = null
) {
    companion object {
        /**
         * Convenient method to build a loading resource.
         */
        fun <T> loading() = Resource<T>(ResourceState.LOADING)

        /**
         * Convenient method to build a success resource with a given data.
         *
         * @param data The data held by the resource.
         */
        fun <T> success(data: T) = Resource(ResourceState.SUCCESS, data)

        /**
         * Convenient method to build a failure resource with a given error message and
         * optionally a [cause][Throwable].
         *
         * @param message The failure error message.
         * @param cause The failure error throwable cause, if exists.
         */
        fun <T> error(message: String, cause: Throwable? = null) = Resource<T>(ResourceState.error(message, cause))
    }
}
