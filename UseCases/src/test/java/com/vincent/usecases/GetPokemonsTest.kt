package com.vincent.usecases

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

    @Test
    fun getPokemons_shouldReturnCorrectResult() {
        given(service.getPokemons()).will { SUCCESS_SEARCH_RESULT }

        val result = GetPokemons(service).execute()

        Mockito.verify(service, times(1)).getPokemons()
        assert(result == SUCCESS_SEARCH_RESULT)
    }

    @Test
    fun getPokemons_shouldReturnNull() {
        given(service.getPokemons()).will { null }

        val result = GetPokemons(service).execute()

        Mockito.verify(service, times(1)).getPokemons()
        assert(result == null)
    }
}
