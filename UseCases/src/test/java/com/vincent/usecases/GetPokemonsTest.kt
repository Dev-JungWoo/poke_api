package com.vincent.usecases

import com.vincent.entities.Pokemon
import com.vincent.usecases.service.IPokeService
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class GetPokemonsTest : BaseUnitTest() {
    @Mock
    lateinit var service: IPokeService

    val MOCK_POKEMONS = listOf(
        Pokemon("poke1", "url1"),
        Pokemon("poke2", "url2"),
        Pokemon("poke3", "url3")
    )

    @Test
    fun getPokemons_shouldReturnCorrectResult() {
        given(service.getPokemons()).will { MOCK_POKEMONS }

        val result = GetPokemons(service).execute()

        Mockito.verify(service, times(1)).getPokemons()
        assert(result == MOCK_POKEMONS)
    }

    @Test
    fun getPokemons_shouldReturnNull() {
        given(service.getPokemons()).will { null }

        val result = GetPokemons(service).execute()

        Mockito.verify(service, times(1)).getPokemons()
        assert(result == null)
    }
}
