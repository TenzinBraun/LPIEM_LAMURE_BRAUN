package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("id") val id: Int
)

data class FriendResponse(
     val friend: List<Friend>? = null,
     val message: Int = 1
)


