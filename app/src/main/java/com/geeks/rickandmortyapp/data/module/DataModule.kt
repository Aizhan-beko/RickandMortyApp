package com.geeks.rickandmortyapp.data.module

import android.util.Log
import com.geeks.rickandmortyapp.network.RickAndMortyApi
import com.geeks.rickandmortyapp.repository.RickAndMortyRepository
import com.geeks.rickandmortyapp.screen.character.CharactersViewModel
import com.geeks.rickandmortyapp.screen.episode.EpisodesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    single { RickAndMortyRepository(get()) }

    viewModel { CharactersViewModel(get()) }

    viewModel { EpisodesViewModel(get()) }

}