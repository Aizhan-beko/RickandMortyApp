package com.geeks.rickandmortyapp.repository.search

import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.data.characters.Character

class SearchRepository (private val api: RickAndMortyApi) {

    suspend fun searchCharacters(name: String): List<Character>? {
        return api.searchCharacters(name).results
    }
}