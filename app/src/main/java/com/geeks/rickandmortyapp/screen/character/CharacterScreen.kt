package com.geeks.rickandmortyapp.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(characters) { character ->
                CharacterItem(character = character) {
                    toCharacterDetailScreen(character.id)
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
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
            imageVector = Icons.Default.Person,
            contentDescription = character.name,
            tint = Color(0xFFFFA500),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFA500)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = character.status,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFFFFA500)
                )
            )
        }
    }
}