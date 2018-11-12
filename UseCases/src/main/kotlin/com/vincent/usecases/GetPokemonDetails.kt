package com.vincent.usecases

import com.vincent.entities.PokemonDetails
import com.vincent.usecases.base.Interactor
import com.vincent.usecases.service.IPokeService

open class GetPokemonDetails(private val service: IPokeService, private val name: String) : Interactor<PokemonDetails> {
    override fun execute(): PokemonDetails? {
        return service.getPokemonDetails(name)
    }
}
