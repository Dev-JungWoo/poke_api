package com.vincent.pokeapi.view.pokemons.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vincent.usecases.service.IPokeService

@Suppress("UNCHECKED_CAST")
class PokemonDetailsViewModelFactory(private val service: IPokeService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonDetailsViewModel(service) as T
    }
}