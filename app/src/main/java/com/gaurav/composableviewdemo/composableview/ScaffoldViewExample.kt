package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldViewExample() {
    // State for Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarVisible by remember { mutableStateOf(false) }

    // Scaffold Layout
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scaffold Example") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation */ }) {
                        Icon(Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search */ }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA), // Toolbar background color
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White// Title text color
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /* Handle home */ }) {
                    Icon(Icons.Default.Home, contentDescription = "Home")
                }
                Spacer(Modifier.weight(1f)) // Spacer for centering
                IconButton(onClick = { /* Handle profile */ }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                snackbarVisible = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hello, Scaffold!",
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
            }

            if (snackbarVisible) {
                LaunchedEffect(snackbarVisible) {
                    snackbarHostState.showSnackbar("FAB clicked!")
                    snackbarVisible = false
                }
            }
        }
    )
}
