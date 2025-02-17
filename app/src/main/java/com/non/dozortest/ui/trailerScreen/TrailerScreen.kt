package com.non.dozortest.ui.trailerScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.non.dozortest.ui.videoPlayer.YouTubePlayer
import com.non.dozortest.viewmodel.MainViewModel

@Composable
fun TrailerScreen(mainViewModel: MainViewModel, navController: NavController) {
    val videoResponse by mainViewModel.videoDetails.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var selectedVideoId by rememberSaveable {
        mutableStateOf(videoResponse.values.firstOrNull() ?: "VLFljn4gdKk")
    }

    LaunchedEffect(videoResponse) {
        videoResponse.takeIf { it.isNotEmpty() }?.let { videos ->
            val videoKey = videos.values.firstOrNull()
            if (videoKey != null) {
                selectedVideoId = videoKey
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
    ) {
        if (!isLandscape) {
            IconButton(
                onClick = { navController.popBackStack() },
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
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (isLandscape) PaddingValues(16.dp) else PaddingValues(0.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            YouTubePlayer(
                youtubeVideoId = selectedVideoId,
                lifecycleOwner = LocalLifecycleOwner.current
            )
        }
    }
}

