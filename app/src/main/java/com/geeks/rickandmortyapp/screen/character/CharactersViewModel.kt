package com.geeks.rickandmortyapp.screen.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.repository.characters.CharactersRepository

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {

    val charactersFlow = repository.getCharactersFlow().cachedIn(viewModelScope)

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            _selectedCharacter.value = repository.getCharacterById(characterId)
        }
    }
}
