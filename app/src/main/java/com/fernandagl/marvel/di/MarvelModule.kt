package com.fernandagl.marvel.di

import com.fernandagl.marvel.data.service.api.MarvelService
import com.fernandagl.marvel.data.repository.*
import com.fernandagl.marvel.data.resource.MarvelRetrofit
import com.fernandagl.marvel.ui.viewmodel.AllCharactersViewModel
import com.fernandagl.marvel.ui.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {

    single { MarvelRetrofit.createService(MarvelService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { AllCharactersViewModel(get()) }

    single<ComicsRepository> { ComicsRepositoryImpl(get()) }

    single<GetComicsByCharacterIdUseCase> { GetComicsByCharacterId(get()) }

    viewModel { CharacterDetailsViewModel(get()) }
}