package com.ranhaveshush.mdb.util

import com.ranhaveshush.mdb.api.ApiClient
import com.ranhaveshush.mdb.api.ClientFactory
import com.ranhaveshush.mdb.repository.HomeRepository
import com.ranhaveshush.mdb.repository.MovieCategoryRepository
import com.ranhaveshush.mdb.repository.MovieDetailsRepository
import com.ranhaveshush.mdb.repository.SearchRepository
import com.ranhaveshush.mdb.viewmodel.HomeViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MovieCategoryViewModelFactory
import com.ranhaveshush.mdb.viewmodel.MovieDetailsViewModelFactory
import com.ranhaveshush.mdb.viewmodel.SearchViewModelFactory

object InjectorUtils {
    fun provideHomeViewModelFactory() = HomeViewModelFactory(getHomeRepository())

    fun provideMovieCategoryViewModelFactory() =
        MovieCategoryViewModelFactory(getMovieCategoryRepository())

    fun provideMovieDetailsViewModelFactory() =
        MovieDetailsViewModelFactory(getMovieDetailsRepository())

    fun provideSearchViewModelFactory() = SearchViewModelFactory(getSearchRepository())

    private fun getHomeRepository() = HomeRepository(getApiClient())

    private fun getMovieCategoryRepository() = MovieCategoryRepository(getApiClient())

    private fun getMovieDetailsRepository() = MovieDetailsRepository(getApiClient())

    private fun getSearchRepository() = SearchRepository(getApiClient())

    private fun getApiClient(): ApiClient = ClientFactory.default
}
