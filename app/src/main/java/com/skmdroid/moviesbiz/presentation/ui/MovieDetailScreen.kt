package com.skmdroid.moviesbiz.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skmdroid.moviesbiz.presentation.viewmodels.MovieDetailViewModel
import com.skmdroid.moviesbiz.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    id: Int
) {

    val viewModel: MovieDetailViewModel = hiltViewModel()
    val movie by viewModel.movie.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        viewModel.getMovieDetail(id)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = movie?.title ?: "") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {
        Surface(modifier = Modifier.padding(it)) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.BASE_URL_IMAGE_W400}${movie?.backdropPath}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(
                    text = "Rating - ${movie?.voteAverage ?: ""}",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 24.sp
                )
                Text(text = movie?.overview ?: "", modifier = Modifier.padding(8.dp))
                Text(
                    text = "Release Date - ${movie?.releaseDate ?: ""}",
                    modifier = Modifier.padding(8.dp)
                )
                Log.d("REsult1Test", "Adult - ${movie?.adult}")
                if (movie?.adult == true) {
                    Row {
                        Text("Adult Content - 18+")
                    }
                }
            }
        }
    }
}