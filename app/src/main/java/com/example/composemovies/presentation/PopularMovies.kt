package com.example.composemovies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composemovies.ui.theme.ComposeMoviesTheme

@Composable
fun PopularMovies(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Popular Movies",
            modifier = modifier
        )
        Button(onClick = { /*TODO call viewModel*/ }) {
            Text(text = "Get Popular Movies")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesPreview() {
    ComposeMoviesTheme {
        PopularMovies()
    }
}
