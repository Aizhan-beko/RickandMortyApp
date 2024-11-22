package com.geeks.rickandmortyapp.screen.location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.repository.locations.LocationsRepository


class LocationsViewModel(private val repository: LocationsRepository) : ViewModel() {

        private val _locations = MutableStateFlow<List<Location>>(emptyList())
        val locations: StateFlow<List<Location>> = _locations

        private val _selectedLocation = MutableStateFlow<Location?>(null)
        val selectedLocation: StateFlow<Location?> = _selectedLocation

        private val _errorMessage = MutableStateFlow<String?>(null)
        val errorMessage: StateFlow<String?> = _errorMessage


        fun getLocations() {
            viewModelScope.launch {
                try {
                    _locations.value = repository.getLocations()
                } catch (e: Exception) {
                    _errorMessage.value = "Error on location download: ${e.message}"
                }
            }
        }
        fun getLocationById(id: Int) {
            viewModelScope.launch {
                try {
                    _selectedLocation.value = repository.getLocationById(id)
                } catch (e: Exception) {
                    _errorMessage.value = "Error on detail location download: ${e.message}"
                    Log.e("LocationsViewModel", "Error in getting location", e)
                }
            }
        }
    }
