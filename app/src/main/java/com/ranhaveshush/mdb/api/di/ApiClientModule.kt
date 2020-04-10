package com.ranhaveshush.mdb.api.di

import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.ClientFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiClientModule {
    @Provides
    fun provideClient(): ApiClient = clientFactory().default

    @Singleton
    fun clientFactory(): ClientFactory = ClientFactory()
}
