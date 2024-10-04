package com.non.dozortest.ui.movieDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.non.dozortest.R
import com.non.dozortest.viewmodel.MainViewModel


@Composable
fun MoviesDetailsScreen(mainViewModel: MainViewModel, navController: NavController) {
    val selectedMovie by mainViewModel.selectedMovieEntity.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500${selectedMovie!!.posterPath}")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_movie_placeholder),
            contentDescription = "poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = {navController.popBackStack()},
            modifier = Modifier
                .padding(16.dp)
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

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth(1f)
                .align(Alignment.BottomCenter)
        ) {
            val maxHeight = this.maxHeight

            val bottomHeight: Dp = maxHeight / 3

            val centerHeight = 200.dp

            val centerPaddingBottom = bottomHeight - centerHeight / 2


            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(bottomHeight),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.8f))
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 110.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        text = selectedMovie!!.description,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(
                            start = 14.dp,
                            end = 14.dp,
                            bottom = centerPaddingBottom
                        )
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
                            .height(centerHeight)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                    )

                    // Play button
                    IconButton(
                        onClick = {
                            navController.navigate("movieTrailer")
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .weight(0.65f)
                        .align(Alignment.CenterVertically)
                        .padding(end = 14.dp),
                    text = selectedMovie!!.originalTitle,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

