package com.geeks.rickandmortyapp.screen.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.repository.episodes.EpisodesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: EpisodesRepository) : ViewModel() {

    val episodesFlow = repository.getEpisodesFlow().cachedIn(viewModelScope)

    private val _selectedEpisode = MutableStateFlow<Episode?>(null)
    val selectedEpisode: StateFlow<Episode?> = _selectedEpisode

    fun getEpisodeById(episodeId: Int) {
        viewModelScope.launch {
            _selectedEpisode.value = repository.getEpisodeById(episodeId)
        }
    }
}