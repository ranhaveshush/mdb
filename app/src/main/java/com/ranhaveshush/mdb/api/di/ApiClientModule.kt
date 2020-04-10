package com.ranhaveshush.mdb.api.di

import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.ClientFactory
import dagger.Module
import dagger.Provides

@Module
class ApiClientModule {
    @Provides
    fun provideClient(factory: ClientFactory): ApiClient = factory.default
}
