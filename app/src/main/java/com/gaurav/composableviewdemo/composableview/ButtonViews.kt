package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ButtonViews() {
    var myButtonColor by remember {
        mutableStateOf(Color.DarkGray)
    }
    var myButtontext by remember {
        mutableStateOf("My Button Gray")
    }
    var myButtonStrokeColor by remember {
        mutableStateOf(Color.Green)
    }
    var myText by remember {
        mutableStateOf("This is gray text")
    }
    var myTextColor by remember {
        mutableStateOf(Color.Gray)
    }
    var isButtonClicked by remember {
        mutableStateOf(false)
    }
    var myCircularButtontext by remember {
        mutableStateOf("Click me")
    }
    var myCircularButtonColor by remember {
        mutableStateOf(Color.DarkGray)
    }
    var isCircularButtonClicked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = myText,
            textAlign = TextAlign.Center,
            color = myTextColor,
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {
                isButtonClicked = !isButtonClicked
                if (isButtonClicked) {
                    myButtonColor = Color.Magenta
                    myButtonStrokeColor = Color.Blue
                    myButtontext = "My Green Button"
                    myText = "This is green text"
                    myTextColor = Color.Magenta
                } else {
                    myButtonColor = Color.DarkGray
                    myButtonStrokeColor = Color.Green
                    myButtontext = "My Gray Button"
                    myText = "This is gray text"
                    myTextColor = Color.Gray
                }
            },
            modifier = Modifier.size(200.dp, 60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myButtonColor),
            border = BorderStroke(1.dp, myButtonStrokeColor),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = myButtontext,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = {
                isCircularButtonClicked=!isCircularButtonClicked
                if (isCircularButtonClicked){
                    myCircularButtontext="Clicked"
                    myCircularButtonColor=Color.Red
                }else{
                    myCircularButtontext="Click me"
                    myCircularButtonColor=Color.DarkGray
                }
            },
            modifier = Modifier.size(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = myCircularButtonColor),
            border = BorderStroke(1.dp, myButtonStrokeColor),
            shape = RoundedCornerShape(100)
        )
        {
            Text(
                text = myCircularButtontext,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }

}

