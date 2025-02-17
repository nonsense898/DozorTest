package com.non.dozortest.ui.savedScreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.non.dozortest.R
import com.non.dozortest.ui.ItemMovie
import com.non.dozortest.ui.mainScreen.EmptyMoviePlaceholder
import com.non.dozortest.viewmodel.MainViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SavedMoviesScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    ) {
    val savedMovies by mainViewModel.allMovies.collectAsState(initial = emptyList())

    if (savedMovies.isEmpty()) {
        EmptyMoviePlaceholder()
    } else {
        LazyColumn(modifier = Modifier.padding(top = 64.dp)) {
            items(items = savedMovies) { movie ->
                var lapVisible by remember { mutableStateOf(false) }

                val animatedLapAlpha by animateFloatAsState(
                    targetValue = if (lapVisible) 1f else 0f,
                    label = "Lap alpha",
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = LinearEasing,
                    )
                )

                ItemMovie(
                    movie = movie,
                    onMovieClick = {
                        mainViewModel.selectMovie(movie)
                        navController.navigate("movieDetail")
                    },
                    onSaveClick = { movie, _ ->
                        mainViewModel.removeMovie(movie)
                    },
                    isSaved = true,
                    animatedVisibilityScope = animatedVisibilityScope,
                    modifier = Modifier .graphicsLayer {
                        lapVisible = true
                        alpha = animatedLapAlpha
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EmptyMoviePlaceholder() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_movies_placeholder),
            contentDescription = "Empty movie list",
            modifier = Modifier.size(200.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "No movies available",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}