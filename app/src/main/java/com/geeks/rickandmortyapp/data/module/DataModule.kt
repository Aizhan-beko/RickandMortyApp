package com.geeks.rickandmortyapp.data.module

import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.paging_source.CharactersPagingSource
import com.geeks.rickandmortyapp.paging_source.EpisodesPagingSource
import com.geeks.rickandmortyapp.paging_source.LocationsPagingSource
import com.geeks.rickandmortyapp.repository.characters.CharactersRepository
import com.geeks.rickandmortyapp.repository.episodes.EpisodesRepository
import com.geeks.rickandmortyapp.repository.locations.LocationsRepository
import com.geeks.rickandmortyapp.screen.character.CharactersViewModel
import com.geeks.rickandmortyapp.screen.episode.EpisodesViewModel
import com.geeks.rickandmortyapp.screen.location.LocationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }
    single { CharactersRepository(get()) }
    single { LocationsRepository(get()) }
    single { EpisodesRepository(get()) }

    single { CharactersPagingSource(get()) }
    single { EpisodesPagingSource(get()) }
    single { LocationsPagingSource(get()) }

    viewModel { CharactersViewModel(get()) }
    viewModel { LocationsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
}