package com.non.dozortest.ui.movieDetailsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.non.dozortest.R
import com.non.dozortest.viewmodel.MainViewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesDetailsScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val hazeState = remember { HazeState() }
    val selectedMovie by mainViewModel.selectedMovieEntity.collectAsStateWithLifecycle()
    var visible by remember { mutableStateOf(false) }

    val genreMap = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )
    fun getGenreNames(genreIds: List<Int>): List<String> {
        return genreIds.mapNotNull { genreMap[it] }
    }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = AppInterpolator.smoothEasing),
        label = "Alpha Animation"
    )

    val translationY by animateFloatAsState(
        targetValue = if (visible) 0f else 20f,
        animationSpec = tween(durationMillis = 1000, easing = AppInterpolator.smoothEasing),
        label = "Translation Animation"
    )

    LaunchedEffect(Unit) {
        visible = true
    }


    Box() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeSource(hazeState, zIndex = 1f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${selectedMovie!!.posterPath}")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_movie_placeholder),
                    contentDescription = "poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(
                                key = "image-${"https://image.tmdb.org/t/p/w500${selectedMovie!!.posterPath}"}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("movieTrailer")
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.5f))
                            .border(2.dp, Color.White, CircleShape)
                            .shadow(8.dp, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 64.dp, bottom = 16.dp, end = 16.dp, start = 16.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        val cardStyle = HazeStyle(
            backgroundColor = Color.Black,
            tints = listOf(HazeTint(Color.Black.copy(alpha = 0.4f))),
            blurRadius = 8.dp,
            noiseFactor = HazeDefaults.noiseFactor,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .align(Alignment.BottomCenter)
                .hazeSource(hazeState, zIndex = 1f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .hazeEffect(state = hazeState, style = cardStyle),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = selectedMovie?.originalTitle ?: "",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 16.dp)
                                .graphicsLayer(
                                    alpha = alpha,
                                    translationY = translationY
                                )
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Yellow)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "IMDB ${
                                    String.format(
                                        "%.1f",
                                        selectedMovie?.voteAverage?.toDoubleOrNull() ?: 0.0
                                    )
                                }",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }


                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(getGenreNames(selectedMovie!!.genreIds)) { genre ->
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Black.copy(0.4f))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = genre,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            }
                        }
                    }

                    Text(
                        text = selectedMovie?.description ?: "",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .graphicsLayer(
                                alpha = alpha,
                                translationY = translationY
                            )
                    )
                }
            }
        }


    }
}


object AppInterpolator {
    val smoothEasing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
}





