package com.example.weatherapp.screens.main


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDecimals
import com.example.weatherapp.widgets.*


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {

    Log.d("TAG", "Main Screen city value:-$city")




    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city ?: "Bharatpur")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {

        MainScaffold(weather = weatherData.data!!, navController)

    }


}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city?.name + "  ,${weather.city?.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp
        )
    }) {
        Column(Modifier.padding(it)) {
            MainContent(data = weather)
        }
    }

}


@Composable
fun MainContent(data: Weather) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(data?.list?.get(0)?.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageIcon = "${data?.list?.get(0)?.weather?.get(0)?.icon}.png")

                Text(
                    text = formatDecimals(data?.list?.get(0)?.temp?.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                data?.list?.get(0)?.weather?.get(0)?.main?.let {
                    Text(
                        text = it,
                        fontStyle = FontStyle.Italic,

                        )
                }

            }

        }
        HumidityWindPressureRow(data)
        Divider()
        SunSetAndSunRiseRow(data)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,

            )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 8.dp, start = 2.dp, end = 2.dp),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(item)

                }


            }

        }


    }


}









