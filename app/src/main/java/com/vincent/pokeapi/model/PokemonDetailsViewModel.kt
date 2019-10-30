package com.vincent.pokeapi.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vincent.entities.Pokemon
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDetailsViewModel(private val pokeService: IPokeService) : ViewModel() {
    lateinit var pokemon: Pokemon

    fun getPokemonDetails(name: String) = liveData {
        withContext(Dispatchers.IO) {
            emit(GetPokemonDetails(pokeService, name).execute())
        }
    }
}
