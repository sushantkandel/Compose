package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onClickItem: (String) -> Unit = {}) {
    var expended by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(150.dp)
            .clickable {
                onClickItem(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                shadowElevation = 4.dp
            ) {

                AsyncImage(

                    model = ImageRequest.Builder(LocalContext.current).data(movie.images[0])
                        .crossfade(true).transformations(CircleCropTransformation()).build(),
                    contentDescription = "poster image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)

                    /*
                     model = movie.images[0],
                     contentDescription = "poster image",
                     contentScale = ContentScale.Fit*/
                )


            }
            Column(
                modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.Center
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "Director : ${movie.director}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(text = "Released : ${movie.year}", style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expended) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Plot:")
                            }

                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Plot:${movie.plot}")
                            }

                        }, modifier = Modifier.padding(10.dp))

                        Divider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Rating: ${movie.rated}",
                            style = MaterialTheme.typography.titleSmall
                        )


                    }
                }



                Icon(
                    imageVector = if (expended) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Drop down arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expended = !expended
                        },
                    tint = Color.DarkGray


                )

            }


        }


    }

}