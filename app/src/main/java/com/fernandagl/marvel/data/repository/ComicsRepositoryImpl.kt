package com.fernandagl.marvel.data.repository

import com.fernandagl.marvel.data.service.api.MarvelService
import com.fernandagl.marvel.model.comics.Comic
import com.fernandagl.marvel.data.resource.Output
import com.fernandagl.marvel.data.resource.parseResponse
import java.lang.Exception

class ComicsRepositoryImpl(
    private val service: MarvelService): ComicsRepository {
    override suspend fun getComicsByCharacterId(characterId: Int): List<Comic> {
        return when (val result = service.getComicsByCharacterId(characterId).parseResponse()) {
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw GetComicsException()
        }
    }
}

interface ComicsRepository {
    suspend fun getComicsByCharacterId(characterId: Int): List<Comic>
}

class GetComicsException: Exception()