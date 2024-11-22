package com.geeks.rickandmortyapp.screen.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.repository.episodes.EpisodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: EpisodesRepository) : ViewModel() {

    private val _episodesMutableStateFlow = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodesMutableStateFlow

    private val _selectedEpisode = MutableStateFlow<Episode?>(null)
    val selectedEpisode: StateFlow<Episode?> = _selectedEpisode


    suspend fun getEpisodes() {
        _episodesMutableStateFlow.value = repository.getEpisodes()
    }

    suspend fun getEpisodeById(id: Int) {
        _selectedEpisode.value = repository.getEpisodeById(id)
    }

    fun filteredList() {
        viewModelScope.launch(Dispatchers.Default) {
            _episodesMutableStateFlow.value.filter {
                it.name == "Ricky"
            }
        }
    }
}