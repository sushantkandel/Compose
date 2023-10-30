package com.example.mcqapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcqapp.data.DataOrException
import com.example.mcqapp.model.QuestionItem
import com.example.mcqapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(
                data = null,
                loading = true,
                exception = Exception("")
            )
        )

    init {
        getAllQuestion()
    }

    private fun getAllQuestion() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = questionRepository.getAllData()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }

        }
    }


}