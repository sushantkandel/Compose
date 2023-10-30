package com.example.mcqapp.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mcqapp.components.QuestionView

@Composable
fun McqHome(questionViewModel: QuestionViewModel = hiltViewModel()) {
    QuestionView(questionViewModel)
}