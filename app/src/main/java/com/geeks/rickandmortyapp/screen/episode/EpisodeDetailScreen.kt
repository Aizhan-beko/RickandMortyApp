package com.geeks.rickandmortyapp.screen.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        if (episode == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            episode?.let {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Name: ${it.name}",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Air Date: ${it.airDate}",
                            fontSize = 25.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )

                    Text(
                        text = "Episode: ${it.episodeCode}",
                            fontSize = 25.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )

                    it.characters.forEach { characterUrl ->
                        Text(
                            text = "Character: $characterUrl",
                                fontSize = 25.sp,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold
                            )
                    }
                }
            }
        }
    }
}