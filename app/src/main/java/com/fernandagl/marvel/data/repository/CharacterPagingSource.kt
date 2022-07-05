package com.fernandagl.marvel.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fernandagl.marvel.constant.Constant
import com.fernandagl.marvel.data.service.api.MarvelService

class CharacterPagingSource(private val apiService: MarvelService): PagingSource<Int, com.fernandagl.marvel.model.character.Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.fernandagl.marvel.model.character.Character> {
        return try {
            val offset = params.key ?: 0

            val response = apiService.getCharacters(offset)
            val responseOffset = response.data.offset
            val totalCharacters = response.data.total

            LoadResult.Page(
                data = response.data.results,
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + LIMIT
                } else null
            )

        }catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.fernandagl.marvel.model.character.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = Constant.LIMIT
    }
}