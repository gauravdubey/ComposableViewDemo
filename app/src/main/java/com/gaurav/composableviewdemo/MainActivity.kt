package com.gaurav.composableviewdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gaurav.composableviewdemo.composableview.ColumnViewWithAlignment
import com.gaurav.composableviewdemo.composableview.RowViewsWithAlignment
import com.gaurav.composableviewdemo.ui.theme.ComposableViewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableViewDemoTheme {

                ColumnViewWithAlignment()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    ComposableViewDemoTheme {
//        Greeting("Android")
//    }
//    ColumnViewWithAlignment()
    RowViewsWithAlignment()
}

