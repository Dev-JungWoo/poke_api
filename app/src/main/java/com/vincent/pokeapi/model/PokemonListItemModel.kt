package com.vincent.pokeapi.model

import com.google.gson.annotations.SerializedName

data class PokemonListItemModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)