package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class (
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pop")
    val pop: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("weather")
    val weather: List<WeatherX>?
)