package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RadioButtonViews() {

    var myBackgroundColor by remember {
        mutableStateOf(Color(0xFF03DAC5))
    }
    var radioIndex by remember {
        mutableStateOf(0)
    }
    val radioOptions = listOf("Red", "Green", "Gray", "Yellow")
    val radioColors = listOf(Color.Red, Color.Green, Color.LightGray, Color.Yellow)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Radio Button Example",
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f) // Enables advanced drawing
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6200EA), Color(0xFF03DAC5))
                    )
                )
                .padding(start = 40.dp, end = 40.dp, top = 5.dp, bottom = 5.dp),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White // Text color set to transparent to enable gradient
        )
        Column(modifier = Modifier.padding(16.dp)) {
            radioOptions.forEachIndexed { position, name ->
                Row(
                    modifier = Modifier.clickable {
                        radioIndex = position
//                        myBackgroundColor = radioColors[radioIndex]
                    },
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    RadioButton(
                        selected = name == radioOptions[radioIndex],
                        onClick = {
                            radioIndex = position
//                            myBackgroundColor = radioColors[radioIndex]
                        },
                        colors = RadioButtonDefaults.colors(Color.Black)
                    )
                    Text(text = name)
                }
            }
        }

        Button(
            onClick = {
                myBackgroundColor = radioColors[radioIndex]
            })
        {
            Text(text = "Change the background")

        }
    }
}