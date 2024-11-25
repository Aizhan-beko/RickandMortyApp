package com.geeks.rickandmortyapp.screen.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.repository.locations.LocationsRepository


class LocationsViewModel(private val repository: LocationsRepository) : ViewModel() {

    val locationsFlow = repository.getLocationsFlow().cachedIn(viewModelScope)

    private val _selectedLocation = MutableStateFlow<Location?>(null)
    val selectedLocation: StateFlow<Location?> = _selectedLocation

    fun getLocationById(locationId: Int) {
        viewModelScope.launch {
            _selectedLocation.value = repository.getLocationById(locationId)
        }
    }
}
