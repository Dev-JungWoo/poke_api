package com.vincent.pokeapi.view.pokemons.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vincent.pokeapi.BaseUnitTest
import com.vincent.pokeapi.CoroutineTestRule
import com.vincent.pokeapi.services.PokeApiService
import com.vincent.usecases.GetPokemonsState
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.isA
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class PokemonListViewModelTest : BaseUnitTest() {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeApiService: PokeApiService

    @Mock
    lateinit var observer: Observer<GetPokemonsState>

    @Test
    fun `WHEN request data THEN return Loading and Loaded state`() {
        //WHEN
        val viewModel = PokemonListViewModel(pokeApiService, coroutinesRule.testDispatcherProvider)
        viewModel.getPokemons().observeForever(observer)

        //THEN
        verify(observer).onChanged(GetPokemonsState.Loading)
        verify(observer).onChanged(isA(GetPokemonsState.Loaded::class.java))
    }
}