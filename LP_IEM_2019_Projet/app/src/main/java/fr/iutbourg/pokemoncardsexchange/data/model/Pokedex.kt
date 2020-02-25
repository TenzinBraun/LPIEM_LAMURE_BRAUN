package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.SerializedName


data class Pokedex(
    @SerializedName("cards") val cards: List<Card>)

data class PokedexResponse(
    val pokedex : Pokedex? = null,
    val error : Int = 0
)

