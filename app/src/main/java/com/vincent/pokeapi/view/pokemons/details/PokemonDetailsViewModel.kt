package com.vincent.pokeapi.view.pokemons.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vincent.entities.Pokemon
import com.vincent.pokeapi.DefaultDispatcherProvider
import com.vincent.pokeapi.DispatcherProvider
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.withContext

class PokemonDetailsViewModel(private val pokeService: IPokeService,
                              private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()) : ViewModel() {
    lateinit var pokemon: Pokemon

    fun getPokemonDetails(name: String) = liveData {
        withContext(dispatcherProvider.io()) {
            emit(GetPokemonDetails(pokeService, name).execute())
        }
    }
}
