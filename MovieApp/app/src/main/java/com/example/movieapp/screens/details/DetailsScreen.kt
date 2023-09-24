package com.example.movieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {
    Scaffold(topBar = { MyDetailsTopAppBar(navController) }) { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),

            ) {

            DetailsContent(navController, movieData)

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailsContent(
    navController: NavController? = null,
    movieData: String? = getMovies().first().id
) {
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieData

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MovieRow(newMovieList.first())
        Spacer(modifier = Modifier.size(10.dp))
        Divider(modifier = Modifier.padding(10.dp))
        Text(text = "Movie images", style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(10.dp))
        HorizontalScorllableImageView(newMovieList)

        //Text(text = newMovieList[0].title, style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HorizontalScorllableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(items = newMovieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(width = 250.dp, height = 150.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
            ) {

                AsyncImage(

                    model = ImageRequest.Builder(LocalContext.current).data(image)
                        .crossfade(true).build(),
                    contentDescription = "poster image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RectangleShape)

                    /*
                         model = movie.images[0],
                         contentDescription = "poster image",
                         contentScale = ContentScale.Fit*/
                )

            }

        }
    }
}

@Composable
fun MyDetailsTopAppBar(navController: NavController) {
    SmallTopAppBar(title = { Text(text = "Details", modifier = Modifier.offset(x = 12.dp)) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Gray.copy(alpha = 0.4f)
        ),
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "back button",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
        })
}