package com.example.mcqapp.data

data class DataOrException<T, Boolean, Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var exception: Exception? = null
)
