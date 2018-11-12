package com.vincent.pokeapi.data

import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails

interface PokeDataSource {
    fun getPokemons(): List<Pokemon>?
    fun getPokemonDetails(name: String): PokemonDetails?
}

