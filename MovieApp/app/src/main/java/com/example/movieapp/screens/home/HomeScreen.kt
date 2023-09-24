package com.example.movieapp.screens.home

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController) {
    MyScaffoldLayout(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(navController: NavController) {
    Scaffold(topBar = { MyTopAppBar() }) { paddingValues ->
        // rest of the app's UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),

            ) {
            MainContent(navController)
        }
    }
}


@Composable
fun MyTopAppBar() {
    SmallTopAppBar(
        title = { Text(text = "Movie") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Gray.copy(alpha = 0.4f)
        )
    )
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movieItem ->
                    navController.navigate(MovieScreens.DetailsScreen.name + "/$movieItem")
                    Log.d(ContentValues.TAG, "MainContent: $movieItem ")

                }
            }
        }

    }
}

