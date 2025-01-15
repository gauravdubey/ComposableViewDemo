package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Preview
@Composable
fun CountdownScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        DigitalCountdownTimer(initialTimeInSeconds = 3600) // 1-hour countdown
    }
}


@Composable
fun DigitalCountdownTimer(initialTimeInSeconds: Int) {
    // State to hold the remaining time
    var timeLeft by remember { mutableStateOf(initialTimeInSeconds) }

    // LaunchedEffect to update the timer
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L) // Delay by 1 second
            timeLeft--
        }
    }

    // Convert seconds to a digital format (HH:mm:ss)
    val hours = timeLeft / 3600
    val minutes = (timeLeft % 3600) / 60
    val seconds = timeLeft % 60
    val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    // UI for the digital timer
    Text(
        text = timeString,
        style = TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.White
        ),
        modifier = Modifier
            .background(Color.Black, RoundedCornerShape(8.dp))
            .padding(16.dp)
    )
}
