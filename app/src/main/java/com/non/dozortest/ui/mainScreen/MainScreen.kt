package com.non.dozortest.ui.mainScreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.non.dozortest.R
import com.non.dozortest.ui.ItemMovie
import com.non.dozortest.viewmodel.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val lazyMovieItems = mainViewModel.movies.collectAsLazyPagingItems()
    val videoResponse by mainViewModel.videoDetails.collectAsStateWithLifecycle()

    var movieId by remember { mutableIntStateOf(0) }
    var selectedVideoId by remember { mutableStateOf("VLFljn4gdKk") }

    val movieItemsLocal = mainViewModel.allMovies.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(top = 30.dp)) {
        if (lazyMovieItems.itemCount == 0) {
            EmptyMoviePlaceholder()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(
                    lazyMovieItems.itemCount,
                    key = { index ->
                        lazyMovieItems[index]!!.id
                    }
                ) { index ->
                    val movie = lazyMovieItems[index]
                    if (movie != null) {
                        val isSaved =
                            movieItemsLocal.value.any { savedMovie -> savedMovie.id == movie.id }

                        ItemMovie(
                            movie = movie,
                            onMovieClick = { selectedMovie ->
                                mainViewModel.selectMovie(selectedMovie)
                                movieId = selectedMovie.id
                                mainViewModel.getMovieVideos(movieId)
                                navController.navigate("movieDetail")
                            },
                            onSaveClick = { movie, isSaved ->
                                if (isSaved) {
                                    mainViewModel.insertMovie(movie)

                                } else {
                                    mainViewModel.removeMovie(movie)
                                }

                            },
                            isSaved = isSaved
                        )
                    }
                }
            }
        }
    }

    videoResponse.values.takeIf { it.isNotEmpty() }?.let { videos ->
        selectedVideoId = videos.firstOrNull().toString()
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
        Image(
          painter = painterResource(R.drawable.ic_movies_placeholder),
            contentDescription = "Empty movie list",
            modifier = Modifier.size(200.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseSurface)
        )

        Text(
            text = "No movies available",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}