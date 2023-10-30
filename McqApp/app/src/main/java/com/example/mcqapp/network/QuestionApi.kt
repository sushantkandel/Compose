package com.example.mcqapp.network

import com.example.mcqapp.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {

    companion object{
        const val QUESTION_URL="world.json"
    }


    @GET(QUESTION_URL)
    suspend fun getAllQuestion(): Question
}