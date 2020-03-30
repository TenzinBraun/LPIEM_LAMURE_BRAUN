package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.MyFriends
import fr.iutbourg.pokemoncardsexchange.data.model.AllPeople
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import fr.iutbourg.pokemoncardsexchange.data.model.RelationShip
import retrofit2.Response
import retrofit2.http.*

interface FriendApi {
    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLFriend.ALL_PEOPLE)
    suspend fun getPeople(@Header("token") token: String): Response<AllPeople>

    @GET(ApiURLFriend.ALL_FRIEND)
    suspend fun getFriends(@Header("token")token: String): Response<MyFriends>

    @POST(ApiURLFriend.ADD_FRIEND)
    @FormUrlEncoded
    suspend fun addFriends(
        @Field("userID") friendID: Int,
        @Path("userID") userID: Int,
        @Header("token") token: String
    ): Response<RelationShip>



    companion object API {

        const val BASE_URL: String = "http://pokemontcg.ddns.net/api/user/"

        object ApiURLFriend {
            const val ALL_PEOPLE: String = "users"
            const val ALL_FRIEND: String = "friends"
            const val ADD_FRIEND: String = "friends/{userID}"
        }
    }
}
