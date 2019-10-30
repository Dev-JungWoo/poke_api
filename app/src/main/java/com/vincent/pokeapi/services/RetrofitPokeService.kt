package com.vincent.pokeapi.services

import com.vincent.pokeapi.model.PokemonListResultModel
import com.vincent.pokeapi.model.details.PokemonDetailsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitPokeService {
    @GET("/api/v2/pokemon")
    fun getPokemons(): Call<PokemonListResultModel>?

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonDetails(
        @Path("id") id: String
    ): Call<PokemonDetailsModel>?

    companion object {
        const val BASE_URL = "https://pokeapi.co"
    }
}
