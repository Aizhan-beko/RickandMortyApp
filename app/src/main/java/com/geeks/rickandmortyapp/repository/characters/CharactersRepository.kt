package com.geeks.rickandmortyapp.repository.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.paging_source.CharactersPagingSource


class CharactersRepository(
    private val api: RickAndMortyApi
) {
    fun getCharactersFlow() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CharactersPagingSource(api) }
    ).flow

    suspend fun getCharacterById(characterId: Int): Character {
        return api.getCharacterById(characterId)
    }
}
