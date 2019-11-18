package com.ranhaveshush.mdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.Repository

/**
 * This singleton object is a generic [ViewModelProvider.Factory] producer.
 * Specific factory producers implementations use this singleton object to create,
 * specific view model factories.
 */
object ViewModelFactoryProducer {
    /**
     * Creates a [ViewModelProvider.Factory] producer lambda function,
     * for the given [ViewModel] class, the [Repository] class used by
     * the view model constructor and the [ApiClient] used by
     * the repository constructor.
     */
    fun of(
        viewModelClass: Class<out ViewModel>,
        repositoryClass: Class<out Repository>,
        client: ApiClient = ClientFactory.default
    ): () -> ViewModelProvider.Factory {
        return { ViewModelFactory(viewModelClass, repositoryClass, client) }
    }
}

/**
 * A factory for creating [viewModelClass] initialized with [repositoryClass].
 */
internal class ViewModelFactory(
    private val viewModelClass: Class<out ViewModel>,
    private val repositoryClass: Class<out Repository>,
    private val client: ApiClient
) : ViewModelProvider.Factory {
    override fun <ViewModelT : ViewModel> create(modelClass: Class<ViewModelT>): ViewModelT {
        val repository = repositoryClass.getConstructor(ApiClient::class.java).newInstance(client)

        return when {
            viewModelClass.isAssignableFrom(modelClass) -> modelClass.getConstructor(repositoryClass).newInstance(
                repository
            )
            else -> TODO("$modelClass is not supported, expecting ${viewModelClass::class.java}.")
        }
    }
}
