package com.geeks.rickandmortyapp.repository.locations


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.paging_source.LocationsPagingSource

class LocationsRepository(
    private val api: RickAndMortyApi
) {
    fun getLocationsFlow() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { LocationsPagingSource(api) }
    ).flow
    suspend fun getLocationById(locationId: Int): Location {
        return api.getLocationById(locationId)
    }
}