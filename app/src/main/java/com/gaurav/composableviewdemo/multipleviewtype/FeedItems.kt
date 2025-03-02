package com.gaurav.composableviewdemo.multipleviewtype

sealed class FeedItems {
    data class TextPost(val id: Int, val content: String) : FeedItems()
    data class ImagePost(val id: Int, val imageUrl: String, val caption: String) : FeedItems()
    data class VideoPost(val id: Int, val videoUrl: String, val title: String) : FeedItems()

}