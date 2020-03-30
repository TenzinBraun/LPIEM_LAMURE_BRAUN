package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.SerializedName

data class Propositions(
    val myCard: Card,
    val otherCard: Card
)

data class PropositionsResponse(
    @SerializedName("propositions") val propositions: List<Propositions>? = null,
    val error: Int = 0
)