package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.composableviewdemo.R

@Preview
@Composable
fun DropdownMenuViews() {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Select a country") }
    val countryList = stringArrayResource(id = R.array.country_list)
    var itemPosition by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(60.dp))
        Text(
            text = "Dropdown Views Demo",
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

        Box() {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        dropdownExpanded = true
                    }
            ) {
                Text(
                    text = selectedOptionText,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            dropdownExpanded = true
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow_drop_down),
                    contentDescription = ""
                )
            }
            DropdownMenu(expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false })
            {
                countryList.forEachIndexed { pos, country ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = country,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            itemPosition = pos
                            selectedOptionText = countryList[itemPosition]
                        })

                    if (pos < countryList.lastIndex) {
                        HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)
                    }
                }

            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        if (itemPosition != 0) {
            Text(
                text = "Country : $selectedOptionText",
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f) // Enables advanced drawing
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF6200EA), Color(0xFF03DAC5))
                        ),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 2
            )
        }

    }
}