package com.gaurav.composableviewdemo.composableview.pulseeffect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AdvancedPulseEffectDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround

    ) {
        Text(
            text = "Pulse Effect Demo",
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

        FilledIconButton(
            onClick = {},
            modifier = Modifier
                .doublePulseEffect(targetScale = 2f)
                .size(42.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Mic, contentDescription = null)
        }

        FilledIconButton(
            onClick = {},
            modifier = Modifier
                .doublePulseEffect(
                    targetScale = 2f,
                    brush = Brush.radialGradient(
                        0.6f to Color.Yellow,
                        0.9f to Color.Magenta,
                        1.0f to Color.Red
                    )
                )
                .size(42.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Mic, contentDescription = null)
        }

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.White
            ),
            modifier = Modifier
                .doublePulseEffect(
                    brush = SolidColor(Color.Magenta),
                    shape = RoundedCornerShape(16.dp)
                )
                .height(42.dp)
        ) {
            Text("CLICK ME")
        }
    }
}