package com.geeks.rickandmortyapp.screen.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.geeks.rickandmortyapp.data.characters.Character

@Composable
fun CharacterScreen(
    viewModel: CharactersViewModel = koinViewModel(),
    toCharacterDetailScreen: (id: Int) -> Unit
) {
    val characters by viewModel.characters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    LazyColumn {
        items(characters) { character ->
            CharacterItem(character = character) {
                toCharacterDetailScreen(character.id)
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(Icons.Default.Person, contentDescription = character.name)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = character.status, style = MaterialTheme.typography.bodyLarge)
        }
    }
}