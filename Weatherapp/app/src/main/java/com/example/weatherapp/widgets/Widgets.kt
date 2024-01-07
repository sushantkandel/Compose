package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDateTime
import com.example.weatherapp.utils.formatDecimals


    @Preview
    @Composable
    fun WeatherDetailRow(item: WeatherItem? = null) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
            color = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formatDate(item?.dt).split(",")[0],
                    modifier = Modifier.padding(start = 10.dp)
                )
                WeatherStateImage(imageIcon = "${item?.weather?.get(0)?.icon}.png")
                Surface(
                    modifier = Modifier.padding(5.dp),
                    shape = CircleShape,
                    color = Color(0xFFFFC400)
                ) {
                    Text(
                        text = item?.weather?.get(0)?.description.toString(),
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.caption
                    )

                }

                Text(modifier = Modifier.padding(10.dp),text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(item?.temp?.max) + "º")
                    }
                    withStyle(style = SpanStyle(color = Color.LightGray)) {
                        append(formatDecimals(item?.temp?.min) + "º")
                    }

                })

            }

        }

    }

    @Preview
    @Composable
    fun SunSetAndSunRiseRow(data: Weather? = null) {
        Row(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 6.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.sunrise),
                    contentDescription = "sun rise icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = formatDateTime(data?.list?.get(0)?.sunrise),
                    style = MaterialTheme.typography.caption
                )

            }

            Row(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = formatDateTime(data?.list?.get(0)?.sunset),
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.size(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.sunset),
                    contentDescription = "Sunset",
                    modifier = Modifier.size(20.dp)
                )

            }


        }


    }


    @Composable
    fun WeatherStateImage(imageIcon: String) {
        val imageUrl = Constants.ICON_IMAGE_URL + imageIcon

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
            contentDescription = "weather image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(80.dp)

        )

        /* Image(
             painter = rememberAsyncImagePainter(imageUrl),
             contentDescription = null,
             contentScale = ContentScale.Fit,
             modifier = Modifier
                 .size(80.dp)
         )*/


    }

    @Composable
    fun HumidityWindPressureRow(data: Weather?) {

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.humidity),
                    contentDescription = "humidity icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = "${data?.list?.get(0)?.humidity}%",
                    style = MaterialTheme.typography.caption
                )

            }

            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.pressure),
                    contentDescription = "pressure  icon",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = "${data?.list?.get(0)?.pressure} psi",
                    style = MaterialTheme.typography.caption
                )

            }

            Row(modifier = Modifier.padding(4.dp)) {

                Icon(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = "wind icon",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = "${data?.list?.get(0)?.speed} mph",
                    style = MaterialTheme.typography.caption
                )
            }


        }


    }
