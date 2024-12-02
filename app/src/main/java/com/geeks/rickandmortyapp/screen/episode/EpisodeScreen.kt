package com.geeks.rickandmortyapp.screen.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.extensions.customCardWithDoubleBorders
import org.koin.androidx.compose.koinViewModel


@Composable
fun EpisodeScreen(
    navController: NavController,
    viewModel: EpisodesViewModel = koinViewModel()
) {
    val episodes = viewModel.episodesFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(episodes.itemCount) { index ->
            val episode = episodes[index]
            episode?.let {
                EpisodeItem(episode = it) {
                    navController.navigate("episode_detail_screen/${it.id}")
                }
            }
        }

        episodes.apply {
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
@Composable
fun EpisodeItem(episode: Episode, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .customCardWithDoubleBorders(
                outerBorderColor = Color(0xFFFFA500),
                outerBorderWidth = 6.dp,
                innerBackgroundColor = Color.DarkGray,
                innerPadding = 6.dp
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = episode.name,
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = episode.name,
                color = Color(0xFFFFA500),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = episode.airDate,
                color = Color(0xFFFFA500)
            )
        }
    }
}
