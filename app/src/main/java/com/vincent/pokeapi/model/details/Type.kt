package com.vincent.pokeapi.model.details

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeX
)