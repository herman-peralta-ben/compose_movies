package com.example.composemovies.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composemovies.presentation.common.CommonLoadingComposable
import com.example.composemovies.ui.theme.ComposeMoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose Movies") },
            )
        }

    ) {
        Box(modifier = Modifier.padding(it)) {
            when (val value = state.value) {
                is HomeState.Loading -> CommonLoadingComposable()
                is HomeState.Content -> HomeContentComposable(content = value) {
                    viewModel.getPopularMovies()
                }

                is HomeState.Error -> HomeErrorComposable {
                    viewModel.getPopularMovies()
                }
            }
        }
    }
}

@Composable
private fun HomeContentComposable(
    content: HomeState.Content,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Popular Movies",
        )

        LazyRow {
            items(content.popularMovies) { item ->
                PopularMovieItemComposable(movie = item)
            }
        }

        Button(onClick = onClick) {
            Text(text = "Get Popular Movies")
        }
    }
}

@Composable
private fun HomeErrorComposable(
    onClick: () -> Unit,
) {
    Column {
        Text(text = "Error")
        Button(onClick = onClick) {
            Text(text = "Get Popular Movies")
        }
    }
}

@Composable
private fun PopularMovieItemComposable(movie: String) {
    Column {
        Icon(Icons.Rounded.Info, contentDescription = "")
        Text(text = movie)
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesComposablePreview() {
    val content = HomeState.Content(popularMovies = (1..20).map { "Movie $it" })
    ComposeMoviesTheme {
        HomeContentComposable(content = content) {

        }
    }
}
