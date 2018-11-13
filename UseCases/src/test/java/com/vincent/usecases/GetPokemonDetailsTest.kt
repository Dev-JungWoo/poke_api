package com.vincent.usecases

import com.vincent.entities.PokemonDetails
import com.vincent.usecases.service.IPokeService
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class GetPokemonDetailsTest : BaseUnitTest() {
    @Mock
    lateinit var service: IPokeService

    private val MOCK_NAME = "MOCK_NAME"

    val MOCK_POKEMON_DETAILS = PokemonDetails(
        "testImageUrl",
        10,
        10
    )

    @Test
    fun getPokemonDetails_shouldReturnCorrectResult() {
        given(service.getPokemonDetails(MOCK_NAME)).will { MOCK_POKEMON_DETAILS }

        val result = GetPokemonDetails(service, MOCK_NAME).execute()

        Mockito.verify(service, times(1)).getPokemonDetails(MOCK_NAME)
        assert(result == MOCK_POKEMON_DETAILS)
    }

    @Test
    fun getPokemonDetails_shouldReturnNull() {
        given(service.getPokemonDetails(MOCK_NAME)).will { null }

        val result = GetPokemonDetails(service, MOCK_NAME).execute()

        Mockito.verify(service, times(1)).getPokemonDetails(MOCK_NAME)
        assert(result == null)
    }
}
