package com.vincent.pokeapi

import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseUnitTest {
    @Before
    @Throws
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    companion object {
        val MOCK_NAME = "MOCK_NAME"

        val MOCK_POKEMONS = listOf(
            Pokemon("poke1", "url1"),
            Pokemon("poke2", "url2"),
            Pokemon("poke3", "url3")
        )

        val MOCK_POKEMON_DETAILS = PokemonDetails("testurl", 10, 10)
    }
}