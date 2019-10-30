package com.vincent.pokeapi.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val pokeService: IPokeService) : ViewModel() {
    lateinit var pokemon: Pokemon

    val pokemonDetails = MutableLiveData<PokemonDetails>()

    fun getPokemonDetails(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            GetPokemonDetails(pokeService, name).execute()?.let { pokemonDetails.postValue(it) }
        }
    }
}
