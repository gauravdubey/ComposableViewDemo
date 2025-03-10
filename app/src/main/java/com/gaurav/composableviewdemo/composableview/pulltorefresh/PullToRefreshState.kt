package com.gaurav.composableviewdemo.composableview.pulltorefresh

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PullToRefreshState {
    var isRefreshing by mutableStateOf(false)
        private set

    var progress by mutableStateOf(0f)
        internal set

    fun startRefresh() {
        isRefreshing = true
    }

    fun endRefresh() {
        isRefreshing = false
        progress = 0f
    }

    fun updateProgress(newProgress: Float) {
        if (!isRefreshing) {
            progress = newProgress.coerceIn(0f, 1f)
            if (progress >= 1f) {
                startRefresh()
            }
        }
    }
}