package com.geeks.rickandmortyapp.network

import com.geeks.rickandmortyapp.data.episodes.EpisodesResponse
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.data.characters.CharactersResponse
import com.geeks.rickandmortyapp.data.characters.Character
import retrofit2.http.GET
import retrofit2.http.Path


interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacters(): CharactersResponse

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("api/episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode

    @GET("api/episode")
    suspend fun getEpisodes(): EpisodesResponse
}