package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.utils.Constants
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query(value = "q") query: String,
        @Query(value = "appid") appid: String = Constants.API_KEY,
        @Query(value = "units") units: String = "metric",

        ): Weather
}