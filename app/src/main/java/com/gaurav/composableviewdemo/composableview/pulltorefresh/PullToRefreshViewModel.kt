package com.gaurav.composableviewdemo.composableview.pulltorefresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Data class for our list items
data class Item(val id: Int, val title: String, val description: String)

class PullToRefreshViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadItems()
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            // Simulate network delay
            delay(2000)
            loadItems()
            _isRefreshing.value = false
        }
    }

    private fun loadItems() {
        // Simulate loading data
        val newItems = List(20) { index ->
            Item(
                id = index,
                title = "Item ${index + 1}",
                description = "Description for item ${index + 1}"
            )
        }
        _items.value = newItems
    }
}