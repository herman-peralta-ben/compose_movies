package com.example.composemovies.domain.repository

import com.example.composemovies.domain.model.Resource

interface MoviesRepository {
    suspend fun getPopularMovies(): Resource<List<String>>
}
