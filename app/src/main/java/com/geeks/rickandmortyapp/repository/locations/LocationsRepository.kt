package com.geeks.rickandmortyapp.repository.locations


import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.network.RickAndMortyApi

class LocationsRepository(
    private val api: RickAndMortyApi
) {
    suspend fun getLocations(): List<Location> {
        return api.getLocations().results?: emptyList()
    }
    suspend fun getLocationById(id: Int): Location {
        return api.getLocation(id)
    }
}