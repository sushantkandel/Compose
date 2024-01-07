package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {

        val response = try {
            api.getWeather(query = cityQuery)
        } catch (ex: Exception) {
            Log.d("REX","getWeather :-${ex}")
            return DataOrException(exception = ex)
        }
        Log.d("INSIDE","getWeather :-${response}")
        return DataOrException(data = response)
    }


}