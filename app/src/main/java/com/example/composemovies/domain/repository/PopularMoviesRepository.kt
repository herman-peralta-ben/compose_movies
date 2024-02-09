package com.example.composemovies.domain.repository

interface MoviesRepository {
    suspend fun getPopularMovies(): List<String>
}
