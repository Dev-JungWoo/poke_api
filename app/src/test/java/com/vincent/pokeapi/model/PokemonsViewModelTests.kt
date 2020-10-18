
package com.vincent.pokeapi.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.BaseUnitTest
import com.vincent.pokeapi.CoroutineTestRule
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.pokeapi.view.pokemons.list.PokemonListViewModel
import com.vincent.usecases.GetPokemonsState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class PokemonsViewModelTests : BaseUnitTest() {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeApiService: PokeApiService

    @Mock
    lateinit var observer: Observer<GetPokemonsState>

    val MOCK_POKEMONS = listOf(
        Pokemon("poke1", "url1"),
        Pokemon("poke2", "url2"),
        Pokemon("poke3", "url3")
    )

    @Test
    fun `WHEN request data THEN return empty list`() {
        val emptyPokemonList: List<Pokemon> = listOf()
        var resultList: List<Pokemon>? = null

        given(pokeApiService.getPokemons()).will { emptyPokemonList }

        val viewModel = PokemonListViewModel(pokeApiService, coroutinesTestRule.testDispatcherProvider)
        viewModel.getPokemons().observeForever(observer)

        verify(observer).onChanged(GetPokemonsState.Loading)
    }

    @Test
    fun `When request data THEN return mock data`() {
        var resultList: List<Pokemon>? = null

        given(pokeApiService.getPokemons()).will { MOCK_POKEMONS }

        val viewModel = PokemonListViewModel(pokeApiService, coroutinesTestRule.testDispatcherProvider)

        viewModel.getPokemons().observeForever(observer)

        verify(observer).onChanged(GetPokemonsState.Loading)
    }
}
