package com.ranhaveshush.mdb.vo

/**
 * A generic resource class that holds a [value object][ValueObject] with its [status][Status].
 */
data class Resource<ValueObjectT : ValueObject>(
    val status: Status,
    val data: ValueObjectT? = null,
    val message: String? = null
) {
    companion object {
        fun <ValueObjectT : ValueObject> success(data: ValueObjectT?) = Resource(Status.SUCCESS, data)

        fun <ValueObjectT : ValueObject> error(message: String? = null, data: ValueObjectT? = null) =
            Resource(Status.ERROR, data, message)

        fun <ValueObjectT : ValueObject> loading(data: ValueObjectT? = null) = Resource(Status.LOADING, data)
    }
}
