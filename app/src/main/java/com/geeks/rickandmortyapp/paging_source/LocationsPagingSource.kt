package com.geeks.rickandmortyapp.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.network.RickAndMortyApi

class LocationsPagingSource(
    private val api: RickAndMortyApi
) : PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val page = params.key ?: 1
            val response = api.getLocations(page)

            LoadResult.Page(
                data = response.results.orEmpty(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id -> id
            }
        }
    }
}