package com.vincent.pokeapi.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.*

class PokemonDetailsViewModel(private val pokeService: IPokeService) : ViewModel(), CoroutineScope {
    lateinit var pokemon: Pokemon
    val pokemonDetails = MutableLiveData<PokemonDetails?>()

    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job

    fun getPokemonDetails(name: String) {
        launch {
            GetPokemonDetails(pokeService, name).execute()?.let { pokemonDetails.postValue(it) }
        }
    }
}
