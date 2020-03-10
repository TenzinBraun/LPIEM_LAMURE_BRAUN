package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userID")
    @Expose
    val userID: Int?,
    @SerializedName("email")
    @Expose
    val  email: String?,
    @SerializedName("name")
    @Expose
    val  name: String?,
    @SerializedName("firstname")
    @Expose
    val firstname: String?,
    @SerializedName("token")
    @Expose
    val token: String?
    //,
    //val toto : String? = null

)

data class UserResponse(
    val friend: List<User>? = null,
    val user : User? = null,
    val error: String? = null
)

