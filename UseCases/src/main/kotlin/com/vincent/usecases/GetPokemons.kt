package com.vincent.usecases

import com.vincent.entities.Pokemon
import com.vincent.usecases.base.Interactor
import com.vincent.usecases.service.IPokeService

open class GetPokemons(private val service: IPokeService) : Interactor<List<Pokemon>> {
    override fun execute(): List<Pokemon>? {
        return service.getPokemons()
    }
}
