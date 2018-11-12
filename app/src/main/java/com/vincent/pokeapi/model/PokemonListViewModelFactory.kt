package com.vincent.pokeapi.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vincent.usecases.service.IPokeService

@Suppress("UNCHECKED_CAST")
class PokemonListViewModelFactory(private val service: IPokeService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonListViewModel(service) as T
    }
}