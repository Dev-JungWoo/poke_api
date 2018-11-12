package com.vincent.pokeapi.data

import com.vincent.entities.Pokemon

interface PokeDataSource {
    fun getPokemons(): List<Pokemon>?
}

