package com.example.weatherapp.utils

import java.text.SimpleDateFormat

fun formatDate(timestamp: Int?): String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp?.toLong()?.times(1000) ?: 0)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Int?): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp?.toLong()?.times(1000) ?: 0)

    return sdf.format(date)
}

fun formatDecimals(item: Double?): String {
    return " %.0f".format(item)
}