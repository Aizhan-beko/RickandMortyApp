package com.geeks.rickandmortyapp.repository.episodes

import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.network.RickAndMortyApi

class EpisodesRepository(
    private val api: RickAndMortyApi
) {
    suspend fun getEpisodes(): List<Episode> {
        return api.getEpisodes().results ?: emptyList()
    }
    suspend fun getEpisodeById(id: Int): Episode {
        return api.getEpisode(id)
    }
}