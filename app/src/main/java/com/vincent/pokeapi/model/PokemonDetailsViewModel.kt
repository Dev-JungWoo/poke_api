package com.vincent.pokeapi.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
