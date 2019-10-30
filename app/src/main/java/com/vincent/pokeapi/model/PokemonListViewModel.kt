package com.vincent.pokeapi.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vincent.entities.Pokemon
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(private val pokeService: IPokeService) : ViewModel() {
    val pokemons = MutableLiveData<List<Pokemon>>()

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            GetPokemons(pokeService).execute()?.let { pokemons.postValue(it) }
        }
    }
}
