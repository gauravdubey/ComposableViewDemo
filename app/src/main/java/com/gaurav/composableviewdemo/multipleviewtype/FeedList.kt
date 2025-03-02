package com.gaurav.composableviewdemo.multipleviewtype

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

val feedItems = listOf(
    FeedItems.TextPost(1, "This is a text post."),
    FeedItems.ImagePost(2, "https://picsum.photos/600/300", "A beautiful sunset!"),
    FeedItems.VideoPost(3, "https://www.pexels.com/video/child-playing-with-toy-and-adult-reading-book-6565100/", "Funny cat video"),
    FeedItems.TextPost(4, "Another text post."),
)

@Composable
fun SocialMediaFeeds() {
    FeedList(feedItems=feedItems)
}
@Composable
fun FeedList(feedItems: List<FeedItems>) {

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