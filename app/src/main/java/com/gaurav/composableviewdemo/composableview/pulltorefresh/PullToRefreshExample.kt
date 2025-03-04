package com.gaurav.composableviewdemo.composableview.pulltorefresh

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

val listItems = listOf(
    "Arabic",
    "Bacon Ipsum",
    "Baseball Ipsum",
    "Bavaria Ipsum",
    "Beer Ipsum",
    "Bowie Ipsum",
    "Cheese Ipsum",
    "Corporate Ipsum",
    "Cupcake Ipsum",
    "Cyrillic",
    "Esperanto",
    "Fishier Ipsum",
    "Gangsta Ipsum",
    "Gibberish Ipsum",
    "Greek",
    "Hebrew",
    "Hindi",
    "Hipster Ipsum",
    "Interlingua",
    "L33tspeak",
    "Lorem Ipsum",
    "Luxembourgish",
    "Pommy Ipsum",
    "Quenya",
    "Slovio",
    "Sona",
    "Space Ipsum",
    "Tokipona"
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowListOfPullToRefresh() {
    var isRefreshing = false
    PullToRefreshCustomStyleSample(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshCustomStyleSample(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = state
            )
        },
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(listItems) {
                ListItem(
                    {
                        Text(text = it)
                    }
                )
            }
        }
    }
}