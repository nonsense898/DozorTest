@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.non.dozortest.ui.mainScreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.non.dozortest.R
import com.non.dozortest.ui.ItemMovie
import com.non.dozortest.viewmodel.MainViewModel

@Composable
fun SharedTransitionScope.MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val lazyMovieItems = mainViewModel.movies.collectAsLazyPagingItems()
    val movieItemsLocal = mainViewModel.allMovies.collectAsState()
    val isRefreshing = lazyMovieItems.loadState.refresh is LoadState.Loading
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(top = 64.dp)) {
        NotesHeader(textState, mainViewModel, modifier = Modifier) {
            mainViewModel.setGenre(it)
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { lazyMovieItems.refresh() }
        ) {
            if (lazyMovieItems.itemCount == 0) {
                EmptyMoviePlaceholder()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        lazyMovieItems.itemCount,
                        key = { index ->
                            lazyMovieItems[index]?.id ?: 0
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
                                    mainViewModel.getMovieVideos(selectedMovie.id)
                                    navController.navigate("movieDetail")
                                },
                                onSaveClick = { movie, isSaved ->
                                    if (isSaved) {
                                        mainViewModel.insertMovie(movie)
                                    } else {
                                        mainViewModel.removeMovie(movie)
                                    }
                                },
                                isSaved = isSaved,
                                animatedVisibilityScope = animatedVisibilityScope,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NotesHeader(
    textState: MutableState<TextFieldValue>,
    mainViewModel: MainViewModel,
    modifier: Modifier,
    onSelectedGenre: (Int) -> Unit
) {
    val genreMap = mapOf(
        "Action" to 28,
        "Adventure" to 12,
        "Animation" to 16,
        "Comedy" to 35,
        "Crime" to 80,
        "Documentary" to 99,
        "Drama" to 18,
        "Family" to 10751,
        "Fantasy" to 14,
        "History" to 36,
        "Horror" to 27,
        "Music" to 10402,
        "Mystery" to 9648,
        "Romance" to 10749,
        "Science Fiction" to 878,
        "TV Movie" to 10770,
        "Thriller" to 53,
        "War" to 10752,
        "Western" to 37
    )

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .zIndex(1f)
                .border(2.dp, Color.Gray.copy(0.4f), shape = RoundedCornerShape(12.dp)),
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TextField(
                        value = textState.value,
                        onValueChange = {
                            textState.value = it
                            mainViewModel.setQuery(it.text)
                        },
                        placeholder = {
                            Text(
                                text = "Search your movies",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Start
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )

                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp),
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "searchIcon",
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(genreMap.keys.toList()) { genreName ->
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSystemInDarkTheme()) {
                                Color(0xFF424242)
                            } else {
                                Color(0xFFE0E0E0)
                            }
                        )
                        .padding(8.dp)
                        .clickable {
                            val genreId = genreMap[genreName]
                            genreId?.let {
                                onSelectedGenre(it)
                            }
                        }
                ) {
                    Text(
                        text = genreName,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
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

