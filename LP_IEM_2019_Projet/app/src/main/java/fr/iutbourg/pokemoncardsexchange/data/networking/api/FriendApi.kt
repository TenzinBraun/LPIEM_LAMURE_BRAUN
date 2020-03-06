package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.Friend
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Response
import retrofit2.http.GET

interface FriendApi {
    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLFriend.ALL_FRIEND)
    suspend fun getAllFriends(): Response<List<Friend>>
    companion object API{

        const val BASE_URL: String = "https://api.pokemontcg.io/v1/"

        object ApiURLFriend {
            const val ALL_FRIEND: String = "cards?subtype=EX"
        }
    }
}
