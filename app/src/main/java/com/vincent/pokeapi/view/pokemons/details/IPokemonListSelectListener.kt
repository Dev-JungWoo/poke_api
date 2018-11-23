package com.vincent.pokeapi.view.pokemons.details

import com.vincent.entities.Pokemon

interface IPokemonListSelectListener {
    fun onSelect(pokemon: Pokemon)
}