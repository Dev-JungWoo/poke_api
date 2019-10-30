package com.vincent.pokeapi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.usecases.GetPokemonDetails
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailsViewModel(private val pokeService: IPokeService) : ViewModel() {
    lateinit var pokemon: Pokemon

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                GetPokemonDetails(pokeService, name).execute()
            }?.let {
                _pokemonDetails.value = it
            }
        }
    }
}
