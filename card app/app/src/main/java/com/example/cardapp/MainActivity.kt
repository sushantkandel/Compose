package com.example.cardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardapp.ui.theme.CardAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CreateCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp)
            ),
            elevation = 4.dp
        ) {

            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider(
                    modifier = Modifier.fillMaxWidth(), startIndent = 12.dp
                )

                CreateInfo()

                Button(onClick = {

                    buttonClickedState.value = !buttonClickedState.value

                }) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.button
                    )
                }

                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box {}
                }


            }

        }
    }

}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Sushant Kandel",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(3.dp),
            text = "An Android programmer in Swift.",


            )
        Text(
            modifier = Modifier.padding(3.dp),
            text = "peaceandray@gmail.com",
            style = MaterialTheme.typography.subtitle1,

            )

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile pic image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.FillBounds
        )

    }
}

@Preview(showBackground = false)
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {

            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))

        }

    }


}

@Composable
fun Portfolio(data: List<String>) {

    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                shape = RectangleShape
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(color = MaterialTheme.colors.surface)

                ) {
                    CreateImageProfile(modifier = Modifier.size(80.dp))
                    Column(modifier = Modifier.padding(8.dp).align(Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great card project", style = MaterialTheme.typography.body2)
                    }

                }

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CardAppTheme {
        CreateCard()
    }
}