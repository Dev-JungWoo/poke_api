package com.vincent.pokeapi.model

import com.google.gson.annotations.SerializedName

data class PokemonListResultModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: Any,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<PokemonListItemModel>
)