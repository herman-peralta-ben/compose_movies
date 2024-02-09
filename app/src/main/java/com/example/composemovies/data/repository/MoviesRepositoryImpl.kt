package com.example.composemovies.data.repository

import com.example.composemovies.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    // TODO
) : MoviesRepository {
    override suspend fun getPopularMovies(): List<String> {
        delay(1000)
        return (1..20).map { "Movie$it" }
    }
}
