package com.geeks.rickandmortyapp.screen.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import com.geeks.rickandmortyapp.data.locations.Location

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
            }
        }

        locations.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth()) }
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
@Composable
fun LocationItem(location: Location, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location: ${location.name}",
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFFFFA500),
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = location.type,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFFFA500))
            )
        }
    }
}