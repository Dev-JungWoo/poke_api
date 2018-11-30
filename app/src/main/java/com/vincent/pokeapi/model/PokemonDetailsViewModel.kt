package com.vincent.pokeapi.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PokemonDetailsViewModel(private val pokeService: IPokeService) : ViewModel() {
    lateinit var pokemon: Pokemon
    val pokemonDetails = MutableLiveData<PokemonDetails?>()

    suspend fun getPokemonDetails(name: String) {
        val searchWork = GlobalScope.async { GetPokemonDetails(pokeService, name).execute() }
        val result = searchWork.await()

        result?.let {
            pokemonDetails.postValue(it)
        }
    }
}
