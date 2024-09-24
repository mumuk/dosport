package com.example.dosport.ui.components.generic.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dosport.data.model.ListItem

// TODO: Add scrollbar

@Composable
fun <T : ListItem> GenericList(
    items: List<T>,
    selectedItem: T?,
    onSelect: (T) -> Unit,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            ListItem(
                item = item,
                isSelected = item == selectedItem,
                onSelect = onSelect
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

