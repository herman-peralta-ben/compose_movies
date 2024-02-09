package com.example.composemovies.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovies.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            _state.emit(HomeState.Loading)
            val popularMovies = moviesRepository.getPopularMovies()
            _state.emit(HomeState.Content(popularMovies = popularMovies))
        }
    }
}
