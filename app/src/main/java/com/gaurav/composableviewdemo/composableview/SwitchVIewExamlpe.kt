package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.composableviewdemo.R

@Preview
@Composable
fun SwitchViewsDemo() {

    var switchState by remember {
        mutableStateOf(true)
    }

    var myText by remember {
        mutableStateOf("The Image is visible")
    }

    var alphaValue by remember {
        mutableStateOf(1F)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(100.dp))
        Text(
            text = "Switch Views Demo",
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

        if (switchState){
            alphaValue=1F
            myText="The Image is visible"
        }else{
            alphaValue=0F
           myText="The Image is not visible"
        }
        Switch(
            checked = switchState,
            onCheckedChange = {
                switchState = it
            },
            colors = SwitchDefaults.colors(
                checkedIconColor = Color.Green,
                checkedTrackColor = Color.Green,
                uncheckedThumbColor = Color.Green,
                uncheckedTrackColor = Color.LightGray

            )
        )
        Spacer(modifier = Modifier.size(20.dp))
        Image(
            painter = painterResource(id = R.drawable.architecture),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp).alpha(alphaValue),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = myText,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Blue)
                .width(300.dp)
                .padding(10.dp)

        )
    }

}