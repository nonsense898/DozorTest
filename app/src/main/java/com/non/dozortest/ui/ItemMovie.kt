package com.non.dozortest.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.non.dozortest.R
import com.non.dozortest.data.entities.Movie


@Composable
fun ItemMovie(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
    onSaveClick: (Movie, Boolean) -> Unit,
    isSaved: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { onMovieClick(movie) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_movie_placeholder),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        onSaveClick(movie, !isSaved)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(
                            if (isSaved) R.drawable.ic_saved_filled else R.drawable.ic_saved
                        ),
                        contentDescription = if (isSaved) "Unsave movie" else "Save movie",
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewItemMovie() {
    val mockMovie = Movie(
        1,
        "Sample Movie with a Very Long Title That Might Wrap",
        "This is a sample movie description that goes on for a while to test how it looks with multiple lines.",
        "",
        "311",
        "d33",
        "fd3d3"
    )

    ItemMovie(
        movie = mockMovie,
        onMovieClick = {},
        onSaveClick = { _, _ -> },
        isSaved = false
    )
}