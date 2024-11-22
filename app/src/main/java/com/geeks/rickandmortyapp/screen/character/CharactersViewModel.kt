package com.geeks.rickandmortyapp.screen.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmortyapp.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.geeks.rickandmortyapp.data.characters.Character


class CharactersViewModel(private val repository: RickAndMortyRepository) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getCharacters() {
        viewModelScope.launch {
            try {
                _characters.value = repository.getCharacters()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load characters: ${e.message}"
            }
        }
    }

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedCharacter.value = repository.getCharacterById(id)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load character details: ${e.message}"

                Log.d("aijan", "beko")
            }
        }
    }
}