package com.ranhaveshush.mdb.repository

import com.ranhaveshush.mdb.api.ApiClient

/**
 * A base repository for the specific repositories implementations.
 */
abstract class Repository(protected val client: ApiClient)
