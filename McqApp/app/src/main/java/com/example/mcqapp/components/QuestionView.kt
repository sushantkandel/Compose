package com.example.mcqapp.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcqapp.model.QuestionItem
import com.example.mcqapp.screens.QuestionViewModel
import com.example.mcqapp.utils.AppColors

@Composable
fun QuestionView(questionViewModel: QuestionViewModel) {
    val questions = questionViewModel.data.value.data?.toMutableList()
    Log.d("SIZE", "QuestionSize:${questions?.size} ")
    if (questionViewModel.data.value.loading == true) {
        Log.d("Loading", "Questions: .....Loading..... ")
    } else {
        if (questions != null) {
            QuestionDisplay(question = questions.first())
        }
    }

    questions?.forEach { questionsItem ->
        Log.d("Result", "Questions: ${questionsItem.question}")

    }


}

// @Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionDisplay(
    question: QuestionItem,
    // viewModel: QuestionViewModel,
    //questionIndex: MutableState<Int>,
    onNextClicked: (Int) -> Unit = {}
) {
    var pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    val choicesState = remember(question) {
        question.choices?.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)

    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)

    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState?.get(it) == question.answer
        }

    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker()
            DrawDottedLine(pathEffect = pathEffect)
            Column {
                question.question?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.Start)
                            .fillMaxHeight(0.3f),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        color = AppColors.mOffWhite
                    )
                }

                //choices

                choicesState?.forEachIndexed { index, answer ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp, brush = Brush.linearGradient(
                                    colors = listOf(
                                        AppColors.mOffDarkPurple, AppColors.mOffDarkPurple
                                    )
                                ), shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topEndPercent = 50,
                                    topStartPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = answerState.value == index,
                            onClick = { updateAnswer(index) },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor =
                                if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green.copy(alpha = 0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        )// end radio button
                        if (answer != null) {
                            val annotatedString = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Light, color =
                                        if (correctAnswerState.value == true && index == answerState.value) {
                                            Color.Green
                                        } else if (correctAnswerState.value == false && index == answerState.value) {
                                            Color.Red
                                        } else {
                                            AppColors.mOffWhite
                                        }, fontSize = 17.sp
                                    )
                                ) {
                                    append(answer)
                                }
                            }
                            Text(text = annotatedString, modifier = Modifier.padding(6.dp))
                        }

                    }

                }

            }

        }

    }

}

@Preview
@Composable
fun QuestionTracker(counter: Int = 10, outOff: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(
                    style = SpanStyle(
                        color = AppColors.mLightGray, fontWeight = FontWeight.Bold, fontSize = 27.sp
                    )
                ) {
                    append("Question $counter/")
                    withStyle(
                        style = SpanStyle(
                            color = AppColors.mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOff")
                    }
                }
            }

        }, modifier = Modifier.padding(20.dp)
    )

}

@Composable
fun DrawDottedLine(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            pathEffect = pathEffect
        )

    }

}