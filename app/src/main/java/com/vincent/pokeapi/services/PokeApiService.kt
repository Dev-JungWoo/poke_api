package com.vincent.pokeapi.services

import com.vincent.entities.Pokemon
import com.vincent.entities.PokemonDetails
import com.vincent.pokeapi.data.PokeDataSource
import com.vincent.usecases.service.IPokeService

class PokeApiService(private val dataSource: PokeDataSource) : IPokeService {
    override fun getPokemons(): List<Pokemon>? {
        return dataSource.getPokemons()
    }

    override fun getPokemonDetails(name: String): PokemonDetails? {
        return dataSource.getPokemonDetails(name)
    }
}