package com.geeks.rickandmortyapp.network

import com.geeks.rickandmortyapp.data.episodes.EpisodesResponse
import com.geeks.rickandmortyapp.data.episodes.Episode
import com.geeks.rickandmortyapp.data.characters.CharactersResponse
import com.geeks.rickandmortyapp.data.characters.Character
import com.geeks.rickandmortyapp.data.locations.Location
import com.geeks.rickandmortyapp.data.locations.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): EpisodesResponse

    @GET("episode/{id}") suspend fun getEpisodeById(@Path("id") id: Int): Episode

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): LocationsResponse

    @GET("location/{id}") suspend fun getLocationById(@Path("id") id: Int): Location

    @GET("character")
    suspend fun searchCharacters(@Query("name") name: String): CharactersResponse
}



