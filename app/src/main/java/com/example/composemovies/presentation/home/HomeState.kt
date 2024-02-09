package com.example.composemovies.presentation.home

sealed interface HomeState {
    data object Loading : HomeState

    data class Content(
        val popularMovies: List<String>,
        // TODO...
    ) : HomeState

    data class Error(
        val throwable: Throwable,
    ) : HomeState
}
