package com.geeks.rickandmortyapp.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.geeks.rickandmortyapp.data.characters.Character
import org.koin.androidx.compose.koinViewModel


@Composable
fun CharacterScreen(
    viewModel: CharactersViewModel = koinViewModel(),
    navController: NavController
) {
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(characters.itemCount) { index ->
            val character = characters[index]
            character?.let {
                CharacterItem(
                    character = it,
                    onClick = { clickedCharacter ->
                        navController.navigate("character_detail_screen/${clickedCharacter.id}")
                    }
                )
            }
        }

        characters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.size(35.dp)) }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.size(35.dp)) }
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
fun CharacterItem(
    character: Character,
    onClick: (Character) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.DarkGray, RoundedCornerShape(8.dp))
            .clickable { onClick(character) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${character.status} • ${character.species}",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}



