package com.non.dozortest.ui.savedScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun SavedMoviesScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()

) {
    val savedMovies by mainViewModel.allMovies.collectAsState(initial = emptyList())

    if (savedMovies.isEmpty()) {
        EmptyMoviePlaceholder()
    } else {
        LazyColumn(modifier = Modifier.padding(top = 30.dp)) {
            items(savedMovies) { movie ->
                ItemMovie(
                    movie = movie,
                    onMovieClick = {
                        mainViewModel.selectMovie(movie)
                        navController.navigate("movieDetail")
                    },
                    onSaveClick = { movie, _ ->
                        mainViewModel.removeMovie(movie)
                    },
                    isSaved = true
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