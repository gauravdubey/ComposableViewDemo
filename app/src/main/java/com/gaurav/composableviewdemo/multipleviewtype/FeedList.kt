package com.gaurav.composableviewdemo.multipleviewtype

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

val feedItems = listOf(
    FeedItems.TextPost(1, "This is a text post."),
    FeedItems.ImagePost(2, "https://picsum.photos/600/300", "A beautiful sunset!"),
    FeedItems.VideoPost(
        3,
        "https://www.pexels.com/video/child-playing-with-toy-and-adult-reading-book-6565100/",
        "Funny cat video"
    ),
    FeedItems.TextPost(4, "Another text post."),
)

@Composable
fun SocialMediaFeeds() {
    FeedList(feedItems = feedItems)
}

@Composable
fun FeedList(feedItems: List<FeedItems>) {
    val isRefreshing = remember { mutableStateOf(false) }
    val items = remember { mutableStateOf(List(10) { "Item #${it + 1}" }) }

    // Swipe Refresh State
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing.value)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {

        }
    ) {

    }

    LazyColumn {
        items(feedItems) { item ->
            when (item) {
                is FeedItems.TextPost -> TextPostItem(item)
                is FeedItems.ImagePost -> ImagePostItem(item)
                is FeedItems.VideoPost -> VideoPostItem(item)
            }
        }
    }
}