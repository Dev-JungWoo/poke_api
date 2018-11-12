package com.vincent.usecases.service

import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails

interface IPokeService {
    fun getPokemons(): List<Pokemon>?
    fun getPokemonDetails(name: String): PokemonDetails?
}