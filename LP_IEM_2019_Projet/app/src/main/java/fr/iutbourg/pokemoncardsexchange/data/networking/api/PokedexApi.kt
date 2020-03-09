package fr.iutbourg.pokemoncardsexchange.data.networking.api

import android.content.SharedPreferences
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils.Companion.getString
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokedexApi {

    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.ALL_CARDS)
    suspend fun getAllCard2(): Response<Pokedex>

    /**
     * Get all cards for User
     * @param token [Header] to get User cards
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.USER_CARDS)
    suspend fun getUserCards(@Header("token") token: String): Response<Pokedex>

    /**
     * Get all cards for Friend ID
     * @param token [String] and userID [Path] [Int]
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.FRIEND_CARDS)
    suspend fun getCardForFriend(@Header("token") token: String,
                                 @Path("userID") userID: Int): Response<Pokedex>

    companion object API{

        const val BASE_URL: String = "http://pokemoncardexchange.ddns.net/api/"

        object ApiURLCard {
            const val ALL_CARDS: String = "cards"
            const val USER_CARDS: String = "user/cards"
            const val FRIEND_CARDS: String = "user/friends/{userID}/cards"
        }
    }
}