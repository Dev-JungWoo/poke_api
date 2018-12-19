package com.vincent.pokeapi.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vincent.entities.Pokemon
import com.vincent.usecases.GetPokemons
import com.vincent.usecases.service.IPokeService
import kotlinx.coroutines.*

class PokemonListViewModel(private val pokeService: IPokeService) : ViewModel(), CoroutineScope {
    val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()

    private val job = Job()
    override val coroutineContext = Dispatchers.Default + job

    fun getPokemons() {
        launch {
            GetPokemons(pokeService).execute()?.let { pokemons.postValue(it) }
        }
    }
}
