package com.example.mcqapp.repository

import android.util.Log
import com.example.mcqapp.data.DataOrException
import com.example.mcqapp.model.QuestionItem
import com.example.mcqapp.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionApi: QuestionApi) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllData(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {

        try {
            dataOrException.loading = true
            dataOrException.data = questionApi.getAllQuestion()
            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (ex: Exception) {
            dataOrException.exception = ex
            Log.d("EXCEPTION", "getAllData:${dataOrException.exception!!.localizedMessage} ")
        }

        return dataOrException

    }

}