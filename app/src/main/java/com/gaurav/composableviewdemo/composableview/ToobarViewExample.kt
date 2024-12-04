package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CompleteToolBarScreen() {
    var actionText by remember {
        mutableStateOf("Action will be shown here")
    }
    Scaffold(
        topBar = {
            ToolbarViewExample(onTextChange = { newText -> actionText = newText })
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Top bar Demo",
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

                Text(
                    text = actionText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarViewExample(onTextChange: (String) -> Unit) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick =
            {
                onTextChange("Navigation icon clicked")
            }
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        title = {
            Column(modifier = Modifier.clickable { onTextChange("Top bar title clicked")}) {
                Text(
                    text = "Top App Bar",
                    color = Color.White,
                    fontSize = 20.sp
                )
                Text(
                    text = "Sub title",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        },

        actions = {
            IconButton(onClick =
            {
                onTextChange("Search icon clicked")
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
            IconButton(onClick =
            {
                onTextChange("Share icon clicked")
            }) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "Share options",
                    tint = Color.White
                )
            }
            IconButton(onClick =
            {
                onTextChange("More options clicked")
            }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EA), // Toolbar background color
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White// Title text color
        )

//                elevation = 8.dp // Elevation of the toolbar
    )
}

