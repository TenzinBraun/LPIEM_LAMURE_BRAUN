package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userID")
    @Expose
    val userID: Int? = null,
    @SerializedName("email")
    @Expose
    val  email: String?= null,
    @SerializedName("name")
    @Expose
    val  name: String? = null,
    @SerializedName("firstname")
    @Expose
    val firstname: String? = null,
    @SerializedName("token")
    @Expose
    val token: String?
)

data class UserResponse(
    val friends: List<User>? = null,
    val user : User? = null,
    val error: String? = null
)

data class AllPeople(
    @SerializedName("users") val myUsers: List<User>)

data class MyFriends(
    @SerializedName("friends") val usersFriend: List<User>)

data class MyFriendResponse(
    val myFriends: MyFriends? = null,
    val error: Int = 0
)
data class FriendsResponse(
    val pokeFriends: AllPeople? = null,
    val error : Int = 0
)