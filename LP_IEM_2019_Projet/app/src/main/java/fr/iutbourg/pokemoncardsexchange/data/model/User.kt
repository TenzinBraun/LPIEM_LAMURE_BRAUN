package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("token") val token: String,
    @SerializedName("userID") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("firstname") val firstName: String,
    @SerializedName("sold") val sold: Double
)

data class FriendResponse(
    val friend: List<User>? = null,
    val message: Int = 1
)


