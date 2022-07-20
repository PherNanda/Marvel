package com.fernandagl.marvel

import com.fernandagl.marvel.data.repository.CharactersRepository
import com.fernandagl.marvel.data.repository.ComicsRepository
import com.fernandagl.marvel.data.repository.ComicsRepositoryImpl
import com.fernandagl.marvel.model.character.Character
import com.fernandagl.marvel.model.character.Data
import com.fernandagl.marvel.model.character.MarvelResponse
import com.fernandagl.marvel.model.character.Thumbnail
import com.fernandagl.marvel.model.comics.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MarvelUnitTest : BaseUnitTest(){

   @Test
    fun testCharacterFavorite() {
        runBlocking {
            val responseMock = MarvelResponse(code=1,
                status="",
                data=Data(
                offset = 1,
                limit = 2,
                total = 3,
                count = 2,
                results = listOf(
                    Character(
                        id = 1, name = "Spiderman",
                        description = "descripción", Thumbnail(path = "", extension = "")
                    ),
                    Character(
                        id = 2, name = "IronMan",
                        description = "descripción", Thumbnail(path = "", extension = "")
                    )
                )))

            Mockito.`when`(repoMock?.getResultStream())
            responseMock.data.results.forEach {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun testComicRepositoryListNotNull() {
        runBlocking {
            val responseMock = createComic()
            Mockito.`when`(repoComicMock.getComicsByCharacterId(1)).thenReturn(responseMock)
                responseMock.forEach {
                    assertNotNull(it)
                }
        }
    }

}

open class BaseUnitTest {

    val repoMock: CharactersRepository? = Mockito.mock(CharactersRepository::class.java)

    val repoComicMock: ComicsRepository = Mockito.mock(ComicsRepositoryImpl::class.java)

    fun createCharacter(): Data {
        return Data(
            offset = 1,
            limit = 2,
            total = 3,
            count = 3,
            results = listOf(
                Character(
                    id = 1, name = "Hulk",
                    description = "descripción", Thumbnail(path = "", extension = "")
                ),
                Character(
                    id = 2, name = "IronMan",
                    description = "descripción", Thumbnail(path = "", extension = "")
                )
            )
        )
    }

    fun createComic(): List<Comic> {

        val comicOne = Comic(
            characters = Characters(
                available = 1,
                collectionURI = "",
                returned = 2
            ),
            creators = Creators(
                available = 2,
                collectionURI = "",
                returned = 3
            ),
            dates = listOf<Date>(),
            description = "",
            diamondCode = "",
            digitalId = 3,
            format = "",
            id = 4,
            images = listOf<Image>(),
            issueNumber = 6,
            modified = "",
            pageCount = 6,
            resourceURI = "https://www.marvel.hulkimage.jpg//",
            thumbnail = com.fernandagl.marvel.model.comics.Thumbnail(extension = "", path = ""),
            title = "",
            urls = listOf<Url>()
        )
        val comicTwo = Comic(
            characters = Characters(
                available = 2,
                collectionURI = "",
                returned = 3
            ),
            creators = Creators(
                available = 1,
                collectionURI = "",
                returned = 2
            ),
            dates = listOf<Date>(),
            description = "",
            diamondCode = "",
            digitalId = 2,
            format = "",
            id = 3,
            images = listOf<Image>(),
            issueNumber = 5,
            modified = "",
            pageCount = 5,
            resourceURI = "https://www.marvel.spidermanimage.jpg//",
            thumbnail = com.fernandagl.marvel.model.comics.Thumbnail(extension = "", path = ""),
            title = "",
            urls = listOf<Url>()
        )

        return listOf(comicOne, comicTwo)
    }
}