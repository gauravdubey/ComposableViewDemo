package com.gaurav.composableviewdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gaurav.composableviewdemo.composableview.CheckboxViews
import com.gaurav.composableviewdemo.composableview.CustomToolBar
import com.gaurav.composableviewdemo.ui.theme.ComposableViewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableViewDemoTheme {
//                ColumnViewWithAlignment()
//                RowViewsWithAlignment()
//                ButtonViews()
                MainScreen()
            }
        }
    }
}


@Preview
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CustomToolBar()
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
//                AllTextFieldViews()
//                ImageViewExample()
                CheckboxViews()
            }
        }
    )
}


