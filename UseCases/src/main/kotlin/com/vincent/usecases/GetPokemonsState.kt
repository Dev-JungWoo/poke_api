package com.vincent.usecases

import com.vincent.entities.Pokemon

sealed class GetPokemonsState {
    object Loading : GetPokemonsState()
    class Loaded(val pokemons: List<Pokemon>) : GetPokemonsState()
    object Failed : GetPokemonsState()
}