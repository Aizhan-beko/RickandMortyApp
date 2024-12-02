package com.geeks.rickandmortyapp.screen.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.extensions.customCardContentPadding
import com.geeks.rickandmortyapp.extensions.customCardWithDoubleBordersAndPadding

@Composable
fun LocationScreen(
    viewModel: LocationsViewModel = koinViewModel(),
    toLocationDetailScreen: (id: Int) -> Unit
) {
    val locations = viewModel.locationsFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(locations.itemCount) { index ->
            val location = locations[index]
            location?.let {
                LocationItem(location = it) {
                    toLocationDetailScreen(it.id)
                }
                LocationItem(location = it) {
                    toLocationDetailScreen(it.id)
                }
            }
        }

        locations.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.size(25.dp)) }
                }

                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.size(25.dp)) }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    item { Text(text = "Error: ${e.error.message}") }
                }
            }

            locations.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.size(25.dp)) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.size(25.dp)) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item { Text(text = "Error: ${e.error.message}") }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item { Text(text = "Error: ${e.error.message}") }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationItem(location: Location, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .customCardWithDoubleBordersAndPadding()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .customCardContentPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location: ${location.name}",
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = location.type,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
                )
            }
        }
    }
}
