package com.vincent.pokeapi.view.pokemons.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vincent.pokeapi.DefaultDispatcherProvider
import com.vincent.pokeapi.DispatcherProvider
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.GetPokemonsState
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.withContext

class PokemonListViewModel(private val pokeService: IPokeService,
                           private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()) : ViewModel() {

    fun getPokemons() = liveData {
        emit(GetPokemonsState.Loading)

        withContext(dispatcherProvider.io()) {
            GetPokemons(pokeService).execute()
        }?.let {
            emit(GetPokemonsState.Loaded(it))
        }
    }
}
