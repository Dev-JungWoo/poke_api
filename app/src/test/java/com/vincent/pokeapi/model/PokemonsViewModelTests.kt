package com.vincent.pokeapi.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.BaseUnitTest
import com.vincent.pokeapi.data.PokeDataSource
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.pokeapi.view.pokemons.list.PokemonListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class PokemonsViewModelTests : BaseUnitTest() {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeDataSource: PokeDataSource

    val MOCK_POKEMONS = listOf(
        Pokemon("poke1", "url1"),
        Pokemon("poke2", "url2"),
        Pokemon("poke3", "url3")
    )

    @Test
    fun viewModel_shouldReturnEmptyList() {
        val emptyPokemonList: List<Pokemon> = listOf()
        var resultList: List<Pokemon>? = null
        val pokeApiService = PokeApiService(pokeDataSource)

        given(pokeDataSource.getPokemons()).will { emptyPokemonList }

        val viewModel = PokemonListViewModel(pokeApiService)
        viewModel.pokemons.observeForever { resultList = it }
        viewModel.getPokemons()

        assert(resultList === emptyPokemonList)
        verify(pokeDataSource, times(1)).getPokemons()
    }

    @Test
    fun viewModel_shouldReturnNonEmptyPokemonList() = run {
        var resultList: List<Pokemon>? = null
        val pokeApiService = PokeApiService(pokeDataSource)

        given(pokeDataSource.getPokemons()).will { MOCK_POKEMONS }

        val viewModel = PokemonListViewModel(pokeApiService)

        viewModel.pokemons.observeForever { resultList = it }
        viewModel.getPokemons()

        assert(resultList === MOCK_POKEMONS)
        verify(pokeDataSource, times(1)).getPokemons()
    }
}
