package com.vincent.pokeapi.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonListViewModel(private val pokeService: IPokeService) : ViewModel() {

    fun getPokemons() = liveData {
        withContext(Dispatchers.IO) {
            GetPokemons(pokeService).execute()
        }?.let {
            emit(it)
        }
    }
}
