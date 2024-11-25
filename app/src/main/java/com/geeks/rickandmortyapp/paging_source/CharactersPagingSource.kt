package com.geeks.rickandmortyapp.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.data.characters.Character


class CharactersPagingSource(
    private val api: RickAndMortyApi
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)

            LoadResult.Page(
                data = response.results.orEmpty(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}