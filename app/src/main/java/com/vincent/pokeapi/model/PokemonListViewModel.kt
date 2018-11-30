package com.vincent.pokeapi.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Pokemon
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PokemonListViewModel(private val pokeService: IPokeService) : ViewModel() {
    val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()

    suspend fun getPokemons() {
        val searchWork = GlobalScope.async { GetPokemons(pokeService).execute() }
        val result = searchWork.await()

        result?.let {
            pokemons.postValue(result)
        }
    }
}
