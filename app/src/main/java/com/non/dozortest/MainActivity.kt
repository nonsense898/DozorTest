package com.non.dozortest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.non.dozortest.ui.mainScreen.MainScreen
import com.non.dozortest.ui.movieDetailsScreen.MoviesDetailsScreen
import com.non.dozortest.ui.noConnectionScreen.NoConnectionScreen
import com.non.dozortest.ui.savedScreen.SavedMoviesScreen
import com.non.dozortest.ui.trailerScreen.TrailerScreen
import com.non.dozortest.viewmodel.MainViewModel
import com.non.dozortest.viewmodel.NetworkViewModel
import com.non.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val networkViewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkViewModel.startNetworkCallback(this)

        setContent {
            val navController = rememberNavController()
            val currentDestination by navController.currentBackStackEntryAsState()
            val isNetworkAvailable by networkViewModel.isNetworkAvailable.collectAsState()

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = {
                        if (currentDestination?.destination?.route !in listOf(
                                "movieDetail",
                                "noConnection",
                                "movieTrailer"
                            )
                        ) {
                            BottomNavigation(navController)
                        }
                    }
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = "movieList",
                    ) {
                        composable("movieList") { MainScreen(navController, mainViewModel) }
                        composable("savedMovies") {
                            SavedMoviesScreen(
                                navController,
                                mainViewModel
                            )
                        }
                        composable("noConnection") { NoConnectionScreen() }
                        composable("movieDetail") {
                            MoviesDetailsScreen(
                                mainViewModel = mainViewModel,
                                navController
                            )
                        }
                        composable("movieTrailer") {
                            TrailerScreen(
                                mainViewModel = mainViewModel,
                                navController
                            )
                        }
                    }
                }

                LaunchedEffect(isNetworkAvailable) {
                    if (isNetworkAvailable) {
                        navController.navigate("movieList")
                    } else {
                        navController.navigate("noConnection")
                    }
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavigation(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 15.dp),
        containerColor = MaterialTheme.colorScheme.surface.copy(),

        ) {
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f)
            ),
            icon = {
                Image(
                    painter = painterResource(if (currentRoute == "movieList") R.drawable.ic_movie_filled else R.drawable.ic_movie),
                    contentDescription = "Movies",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseSurface)
                )
            },
            label = { Text("Movies") },
            selected = currentRoute == "movieList",
            onClick = {
                if (currentRoute != "movieList") {
                    navController.navigate("movieList")
                }
            }
        )
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f)
            ),
            icon = {
                Image(
                    painter = painterResource(if (currentRoute == "movieList") R.drawable.ic_saved else R.drawable.ic_saved_filled),
                    contentDescription = "Saved",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseSurface)
                )
            },
            label = { Text("Saved") },
            selected = currentRoute == "savedMovies",
            onClick = {
                if (currentRoute != "savedMovies") {
                    navController.navigate("savedMovies")
                }
            }
        )
    }
}




