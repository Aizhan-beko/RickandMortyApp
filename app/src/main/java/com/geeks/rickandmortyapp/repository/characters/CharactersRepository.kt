package com.geeks.rickandmortyapp.repository.characters

import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.network.RickAndMortyApi


class CharactersRepository(
    private val api: RickAndMortyApi
) {
    suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results ?: emptyList()
    }
    suspend fun getCharacterById(id: Int): Character {
        return api.getCharacterById(id)
    }
}