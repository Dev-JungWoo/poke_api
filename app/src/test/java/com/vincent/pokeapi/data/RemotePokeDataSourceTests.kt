package com.vincent.pokeapi.data

import com.vincent.pokeapi.BaseUnitTest
import com.vincent.pokeapi.services.RetrofitPokeService
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito.times

@RunWith(JUnit4::class)
class RemotePokeDataSourceTests : BaseUnitTest() {
    @Mock
    lateinit var retrofitPokeService: RetrofitPokeService

    @Test
    fun remotePokeDataSourceGetPokemons_shouldReturnNull() {
        val dataSource = RemotePokeDataSource(retrofitPokeService)

        given(retrofitPokeService.getPokemons()).will { null }

        assert(dataSource.getPokemons() == null)
    }

    @Test
    fun retrofitPokeServiceGetPokemons_shouldBeCalled() {
        val dataSource = RemotePokeDataSource(retrofitPokeService)

        dataSource.getPokemons()

        verify(retrofitPokeService, times(1)).getPokemons()
    }

    @Test
    fun remotePokeDataSourcePokemonDetails_shouldReturnNull() {
        val dataSource = RemotePokeDataSource(retrofitPokeService)

        given(retrofitPokeService.getPokemonDetails(MOCK_NAME)).will { null }

        assert(dataSource.getPokemonDetails(MOCK_NAME) == null)
    }

    @Test
    fun retrofitPokeServicePokemonDetails_shouldBeCalled() {
        val dataSource = RemotePokeDataSource(retrofitPokeService)

        dataSource.getPokemonDetails(MOCK_NAME)

        verify(retrofitPokeService, times(1)).getPokemonDetails(MOCK_NAME)
    }
}