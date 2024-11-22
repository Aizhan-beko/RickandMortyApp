package com.geeks.rickandmortyapp.repository

import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.network.RickAndMortyApi


class RickAndMortyRepository(
    private val api: RickAndMortyApi
) {

    suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results ?: emptyList()
    }

    suspend fun getCharacterById(id: Int): Character {
        return api.getCharacterById(id)
    }

    suspend fun getEpisodes(): List<Episode> {
        return api.getEpisodes().results ?: emptyList()
    }

    suspend fun getEpisodeById(id: Int): Episode {
        return api.getEpisode(id)
    }
}