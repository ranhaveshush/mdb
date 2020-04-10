package com.ranhaveshush.mdb.di

import com.ranhaveshush.mdb.api.di.ApiClientModule
import com.ranhaveshush.mdb.viewmodel.HomeViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MovieCategoryViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModelFactory
import com.ranhaveshush.mdb.viewmodel.SearchViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiClientModule::class])
interface AppComponent {
    fun homeViewModelFactory(): HomeViewModelFactory

    fun movieCategoryViewModelFactory(): MovieCategoryViewModelFactory

    fun movieDetailsViewModelFactory(): MovieDetailsViewModelFactory

    fun searchViewModelFactory(): SearchViewModelFactory
}
