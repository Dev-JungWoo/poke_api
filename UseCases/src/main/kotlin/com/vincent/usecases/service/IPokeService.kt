package com.vincent.usecases.service

import com.vincent.entities.Pokemon

interface IPokeService {
    fun getPokemons(): List<Pokemon>?
}