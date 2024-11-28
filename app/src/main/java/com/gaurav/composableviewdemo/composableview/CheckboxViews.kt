package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CheckboxViews() {
    var myMaleCheckedValue by remember {
        mutableStateOf(false)
    }
    var myFemaleCheckedValue by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf("Select the Gender !")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = selectedText,
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
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = myMaleCheckedValue,
                onCheckedChange =
                {
                    myMaleCheckedValue = it
                    if (myMaleCheckedValue) {
                        selectedText = "Selected Gender is : Male"
                        myFemaleCheckedValue = false
                    } else {
                        selectedText = "Select the Gender !"
                    }
                },
                colors = CheckboxDefaults.colors(Color(0xFF03DAC5))
            )
            Text(
                text = "Male",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF03DAC5)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = myFemaleCheckedValue,
                onCheckedChange =
                {
                    myFemaleCheckedValue = it
                    if (myFemaleCheckedValue) {
                        selectedText = "Selected Gender is : Female"
                        myMaleCheckedValue = false
                    } else {
                        selectedText = "Select the Gender !"
                    }
                },
                colors = CheckboxDefaults.colors(Color(0xFF03DAC5))
            )
            Text(
                text = "Female",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF03DAC5)
            )
        }
    }

}
