package com.geeks.rickandmortyapp.screen.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geeks.rickandmortyapp.data.episodes.Episode
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel


@Composable
fun EpisodeScreen(navController: NavController, viewModel: EpisodesViewModel = koinViewModel()) {
    val episodes by viewModel.episodes.collectAsState()

    LaunchedEffect(Dispatchers.IO) {
        viewModel.getEpisodes()
    }

    viewModel.filteredList()
    LazyColumn {
        items(episodes) { episode ->
            EpisodeItem(episode = episode) {
                navController.navigate("episode_detail_screen/${episode.id}")
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: Episode, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(Icons.Default.Person, contentDescription = episode.name)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.body1
            )
            Text(text = episode.airDate, style = MaterialTheme.typography.body2)
        }
    }
}