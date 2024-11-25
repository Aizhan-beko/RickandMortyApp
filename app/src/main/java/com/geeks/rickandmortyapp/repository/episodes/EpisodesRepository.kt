package com.geeks.rickandmortyapp.repository.episodes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.paging_source.EpisodesPagingSource

class EpisodesRepository(
    private val api: RickAndMortyApi
) {
    fun getEpisodesFlow() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { EpisodesPagingSource(api) }
    ).flow

    suspend fun getEpisodeById(episodeId: Int): Episode {
        return api.getEpisodeById(episodeId)
    }
}