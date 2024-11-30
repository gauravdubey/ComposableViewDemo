package com.gaurav.composableviewdemo.composableview

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.composableviewdemo.R

@Preview
@Composable
fun DialogViewExample() {
    var openDialog by remember { mutableStateOf(false) }
    var textColor by remember {
        mutableStateOf(Color.White)
    }
    val myContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(60.dp))
        Text(
            text = "Dialog Views Demo",
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

        Spacer(modifier = Modifier.size(100.dp))

        Button(
            onClick = { openDialog = true },
            modifier = Modifier.size(200.dp, 60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            border = BorderStroke(1.dp, Color.Green),
            shape = RoundedCornerShape(10.dp),

            ) {
            Text(
                text = "Show Dialog",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }

        if (openDialog) {

            AlertDialog(onDismissRequest = { openDialog = true },

                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_warning),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Dialog Title",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                text = {
                    Text(
                        text = "Do you want to change the color of the button?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                },
                containerColor = Color(0xFF707AD8),
                shape = RoundedCornerShape(10.dp),

                confirmButton = {

                    Button(
                        onClick = {
                            openDialog = false
                            textColor = Color.Red
                            Toast.makeText(myContext, "Confirm button clicked", Toast.LENGTH_SHORT)
                                .show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(
                            text = "Yes",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog = false
                            Toast.makeText(myContext, "Dismiss button clicked", Toast.LENGTH_SHORT)
                                .show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(
                            text = "No",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                })
        }
    }

}