package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Response
import retrofit2.http.GET

interface FriendApi {
    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLFriend.ALL_FRIEND)
    suspend fun getAllFriends(): Response<List<User>>
    companion object API{

        const val BASE_URL: String = "http://pokemoncardexchange.ddns.net/api/"

        object ApiURLFriend {
            const val ALL_FRIEND: String = "cards?subtype=EX"
        }
    }
}
