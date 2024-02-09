package com.example.composemovies.di.viewmodel

import com.example.composemovies.data.repository.MoviesRepositoryImpl
import com.example.composemovies.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMoviesRepository(
        moviesRepository: MoviesRepositoryImpl,
    ): MoviesRepository
}
