package com.example.composemovies.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object AppModule {

    @ViewModelScoped
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }
}
