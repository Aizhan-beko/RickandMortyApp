package com.geeks.rickandmortyapp.screen.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharactersViewModel = koinViewModel()
) {
    val character by viewModel.selectedCharacter.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharacterById(characterId)
    }

    if (character == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        character?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name: ${it.name}", style = MaterialTheme.typography.h6)
                Text(text = "Status: ${it.status}")
                Text(text = "Species: ${it.species}")
                Text(text = "Gender: ${it.gender}")
                Text(text = "Location: ${it.location.name}")
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = "Image of ${it.name}",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}