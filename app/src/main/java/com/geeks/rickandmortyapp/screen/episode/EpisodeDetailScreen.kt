package com.geeks.rickandmortyapp.screen.episode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel


@Composable
fun EpisodeDetailScreen(
    episodeId: Int,
    viewModel: EpisodesViewModel = koinViewModel()
) {

    LaunchedEffect(Dispatchers.IO) {
        viewModel.getEpisodeById(episodeId)
    }

    val episode by viewModel.selectedEpisode.collectAsState()

    if (episode == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        episode?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name: ${it.name}", style = MaterialTheme.typography.h6)
                Text(text = "Air Date: ${it.airDate}")
                Text(text = "Episode: ${it.episodeCode}")


                it.characters.forEach { characterUrl ->
                    Text(text = "Character: $characterUrl")
                }
            }
        }
    }
}