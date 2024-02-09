package com.example.composemovies.data.repository

import com.example.composemovies.data.model.response.Movie
import com.example.composemovies.data.model.response.PopularMovies
import com.example.composemovies.data.networking.BASE_URL
import com.example.composemovies.domain.model.Resource
import com.example.composemovies.domain.repository.MoviesRepository
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

private const val POPULAR_MOVIES = "${BASE_URL}/popular"

class MoviesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient,
) : MoviesRepository {
    override suspend fun getPopularMovies(): Resource<List<String>> {
        return try {
            val movies: List<Movie> = httpClient.get<PopularMovies> {
                url(POPULAR_MOVIES)
            }.movies

            Resource.Success(movies.map { it.originalTitle })
        } catch (e: Exception) {
            e.printStackTrace()

            Resource.Failure(e)
        }
    }
}
