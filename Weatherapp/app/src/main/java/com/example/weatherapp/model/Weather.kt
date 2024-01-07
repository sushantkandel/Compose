package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<WeatherItem>,
    @SerializedName("message")
    val message: Double?
)