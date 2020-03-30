package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RelationShip(
    @SerializedName("flag")
    @Expose
    var flag: String? = null
)

data class RelationShipResponse(
    val relationShip: RelationShip? = null,
    val error: String? = ""
)
