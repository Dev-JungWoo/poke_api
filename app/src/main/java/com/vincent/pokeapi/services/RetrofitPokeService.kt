package com.vincent.pokeapi.services

import com.vincent.pokeapi.model.PokemonListResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitPokeService {
    @GET("/")
    fun getPokemons(): Call<PokemonListResultModel>?

    @GET("/{id}")
    fun getPokemonDetails(
        @Path("id") id: String
    ): Call<PokemonListResultModel>?

    companion object {
        val BASE_URL = "https://pokeapi.co/api/v2/pokemon"
    }
}
