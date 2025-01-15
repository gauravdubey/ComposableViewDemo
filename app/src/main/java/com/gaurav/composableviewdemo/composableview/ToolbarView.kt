package com.gaurav.composableviewdemo.composableview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * This is the simple toolbar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleToolbar() {
    TopAppBar(
        title = { Text(text = "My Toolbar") },
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation click */ }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle action */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
        }
    )
}

/**
 * This is the custom toolbar
 */
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar() {
    TopAppBar(
        title = { Text(text = "Composable Views", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /* Handle search */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
            }
            IconButton(onClick = { /* Handle more options */ }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EA), // Toolbar background color
            titleContentColor = Color.White // Title text color
        )

//                elevation = 8.dp // Elevation of the toolbar
    )
}