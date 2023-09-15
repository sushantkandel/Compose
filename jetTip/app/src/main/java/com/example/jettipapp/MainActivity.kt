package com.example.jettipapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettipapp.components.InputField
import com.example.jettipapp.ui.theme.JetTipAppTheme
import com.example.jettipapp.widget.RoundIconButton


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipAppTheme {
                MyApp {
                    MainContent()
                }

            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colors.background
    ) {
        content()
    }

}

//@Preview()
@Composable
fun TopHead(totalPrice: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        color = colorResource(id = R.color.purple_200)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val total = "%.2f".format(totalPrice)
            Text(
                text = "Total Per Person",
                // style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold


            )
            Text(
                text = "$total",
                //style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun MainContent() {
    Column(modifier = Modifier.padding(12.dp)) {

        BillForm { billAmount ->
            Log.d(TAG, "MainContent:$billAmount ")

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit = {}
) {
    val totalBillState = remember {
        mutableStateOf("")
    }

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TopHead()

    Surface(
        modifier = Modifier
            .padding(all = 2.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(size = 8.dp),
        border = BorderStroke(width = 2.dp, color = Color.LightGray),
        elevation = 5.dp
    ) {

        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValueChanged(totalBillState.value.trim())
                    // on value changed
                    keyboardController?.hide()
                }
            )
            //  if (validState) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Split", modifier.align(Alignment.CenterVertically))
                Spacer(modifier = Modifier.width(150.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 3.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RoundIconButton(imageVector = Icons.Default.Remove, onClick = {

                    })

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "1", Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    RoundIconButton(imageVector = Icons.Default.Add, onClick = {

                    })
                }


            }

            //Tip Row
            Row(modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)) {
                Text(
                    text = "Tip",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(200.dp))

                Text(
                    text = "33$",
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterVertically)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                var percentage = "%.0f".format(sliderPositionState.value*100)
                Text(text = ("${percentage}%").toString())
                Spacer(modifier = Modifier.height(14.dp))
                Slider(value = sliderPositionState.value
                    , onValueChange = { newValue ->
                    sliderPositionState.value = newValue
                },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                steps = 10)

            }


            /* } else {
                 Box() {}
             }*/

        }

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTipAppTheme {
        MyApp {

            MainContent()
        }
    }
}