package com.non.dozortest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.non.dozortest.ui.mainScreen.MainScreen
import com.non.dozortest.ui.movieDetailsScreen.MoviesDetailsScreen
import com.non.dozortest.ui.noConnectionScreen.NoConnectionScreen
import com.non.dozortest.ui.savedScreen.SavedMoviesScreen
import com.non.dozortest.ui.theme.MyApplicationTheme
import com.non.dozortest.ui.trailerScreen.TrailerScreen
import com.non.dozortest.viewmodel.MainViewModel
import com.non.dozortest.viewmodel.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val networkViewModel: NetworkViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkViewModel.startNetworkCallback(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()
            val isNetworkAvailable by networkViewModel.isNetworkAvailable.collectAsState()
            val hazeState = remember { HazeState() }

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .hazeSource(hazeState),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(
                        left = 0.dp,
                        top = 0.dp,
                        right = 0.dp,
                        bottom = 0.dp
                    )
                ) { padding ->
                    SharedTransitionLayout {
                        NavHost(
                            modifier = Modifier.padding(padding),
                            navController = navController,
                            startDestination = "movieList",
                        ) {
                            composable("movieList") {
                                MainScreen(
                                    navController,
                                    mainViewModel,
                                    animatedVisibilityScope = this@composable,
                                )
                            }
                            composable("savedMovies") {
                                SavedMoviesScreen(
                                    navController,
                                    mainViewModel,
                                    animatedVisibilityScope = this@composable
                                )
                            }
                            composable("noConnection") { NoConnectionScreen() }
                            composable("movieDetail") {
                                MoviesDetailsScreen(
                                    mainViewModel = mainViewModel,
                                    navController,
                                    animatedVisibilityScope = this@composable,
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

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                                .hazeEffect(
                                    state = hazeState, style = HazeStyle(
                                        backgroundColor = Color.Black,
                                        tints = listOf(HazeTint(Color.Black.copy(alpha = 0.4f))),
                                        blurRadius = 8.dp,
                                        noiseFactor = HazeDefaults.noiseFactor,
                                    )
                                )
                                .background(Color.Black.copy(alpha = 0.5f))
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            if (navController.currentBackStackEntryAsState().value?.destination?.route !in listOf(
                                    "movieDetail", "noConnection", "movieTrailer"
                                )
                            ) {
                                BottomNavigation(
                                    navController = navController,
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                )
                            }
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

@Composable
fun BottomNavigation(navController: NavController, modifier: Modifier = Modifier) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.inverseSurface.copy(0.3f)
                ),
                icon = {
                    Image(
                        painter = painterResource(
                            if (currentRoute == "movieList") R.drawable.ic_movie_filled
                            else R.drawable.ic_movie
                        ),
                        contentDescription = "Movies",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface)
                    )
                },
                label = { Text("Movies") },
                selected = currentRoute == "movieList",
                onClick = {
                    if (currentRoute != "movieList") {
                        navController.navigate("movieList") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.inverseSurface.copy(0.3f)
                ),
                icon = {
                    Image(
                        painter = painterResource(
                            if (currentRoute == "savedMovies") R.drawable.ic_saved_filled
                            else R.drawable.ic_saved
                        ),
                        contentDescription = "Saved",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface)
                    )
                },
                label = { Text("Saved") },
                selected = currentRoute == "savedMovies",
                onClick = {
                    if (currentRoute != "savedMovies") {
                        navController.navigate("savedMovies") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}



