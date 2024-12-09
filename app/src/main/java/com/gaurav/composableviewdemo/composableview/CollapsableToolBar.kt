package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.composableviewdemo.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsableToolbarExample() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Collapsable Toolbar", fontSize = 24.sp, color = Color.Black
                    )

                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        content = { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ContentWithCollapsingImage(scrollBehavior)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentWithCollapsingImage(scrollBehavior: TopAppBarScrollBehavior) {
    val imageHeight = 240.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image Header
            item {
                Box {
                    Image(
                        painter = painterResource(R.drawable.architecture), // Replace with your drawable
                        contentDescription = "Header Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                    )
                    // Optional gradient overlay
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black)
                                )
                            )
                    )
                    Text(
                        text = "Welcome to Jetpack Scrollable Top bar",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                }
            }

            // Content
            items(50) { index ->
                ListItem(
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    headlineContent = { Text("Item $index") },
                    supportingContent = { Text("This is the description for item $index") },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                if (index < 49) { // Avoid adding a divider after the last item
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }
            }

        }
    }
}