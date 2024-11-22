package com.geeks.rickandmortyapp.network

import com.geeks.rickandmortyapp.data.episodes.EpisodesResponse
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.data.characters.CharactersResponse
import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.data.locations.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode

    @GET("location")
    suspend fun getLocations(): LocationsResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): Location
}