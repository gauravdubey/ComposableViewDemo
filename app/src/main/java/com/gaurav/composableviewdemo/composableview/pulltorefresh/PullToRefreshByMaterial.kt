package com.gaurav.composableviewdemo.composableview.pulltorefresh

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshExampleByMaterial(
    modifier: Modifier = Modifier,
    viewModel: PullToRefreshViewModel = viewModel()
) {
    val items by viewModel.items.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        // Custom pull refresh implementation
        val pullRefreshState = rememberPullToRefreshState()

        // Fix: Properly observe the ViewModel's refresh state
        LaunchedEffect(isRefreshing) {
            if (!isRefreshing && pullRefreshState.isRefreshing) {
                // When ViewModel finishes refreshing, end the pull refresh state
                pullRefreshState.endRefresh()
            }
        }

        // When pull refresh is triggered, start the ViewModel refresh
        LaunchedEffect(pullRefreshState.isRefreshing) {
            if (pullRefreshState.isRefreshing && !isRefreshing) {
                viewModel.refresh()
            }
        }

        Box(
            modifier = Modifier
                .pullToRefresh(pullRefreshState)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    ItemCard(item)
                }
            }

            // Custom refresh indicator
            PullToRefreshIndicator(
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

// Custom pull-to-refresh state
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPullToRefreshState(): PullToRefreshState {
    return remember { PullToRefreshState() }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}

// Extension function to add pull-to-refresh functionality
@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.pullToRefresh(state: PullToRefreshState): Modifier = composed {
    val nestedScrollConnection = remember(state) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // If we're refreshing, don't consume any scroll
                if (state.isRefreshing) return Offset.Zero

                // Only react to downward scrolls when at the top of the list
                if (available.y < 0 || source != NestedScrollSource.Drag) return Offset.Zero

                val newProgress = state.progress + (available.y / 300f) // Adjust sensitivity here
                state.updateProgress(newProgress)

                return available
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                // If we've dragged enough to trigger a refresh, start refreshing
                if (state.progress >= 1f && !state.isRefreshing) {
                    state.startRefresh()
                }
                return super.onPostFling(consumed, available)
            }
        }
    }

    this.nestedScroll(nestedScrollConnection)
}

// Custom pull-to-refresh indicator
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshIndicator(
    state: PullToRefreshState,
    modifier: Modifier = Modifier
) {
    val refreshTriggerPx = with(LocalDensity.current) { 80.dp.toPx() }
    val indicatorHeight = 80.dp * state.progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .height(if (state.isRefreshing) 80.dp else indicatorHeight)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isRefreshing) {
            // Show circular progress indicator when refreshing
            CircularProgressIndicator(
                modifier = Modifier.size(36.dp),
                color = MaterialTheme.colorScheme.primary
            )
        } else if (state.progress > 0) {
            // Show custom indicator when pulling
            val rotationAngle by animateFloatAsState(
                targetValue = state.progress * 360f,
                label = "rotation"
            )

            val color by animateColorAsState(
                targetValue = if (state.progress >= 1f)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.primary.copy(alpha = state.progress),
                label = "color"
            )

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Pull to refresh",
                modifier = Modifier
                    .size(36.dp)
                    .rotate(rotationAngle),
                tint = color
            )

            if (state.progress >= 1f) {
                Text(
                    text = "Release to refresh",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 50.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    text = "Pull to refresh",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 50.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = state.progress)
                )
            }
        }
    }
}
