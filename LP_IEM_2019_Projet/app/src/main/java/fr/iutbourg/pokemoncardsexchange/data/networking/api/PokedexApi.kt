package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.CardResponse
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @POST(ApiURLCard.ADD_NEW_CARD)
    suspend fun addCardToUserDB(@Header("token") token: String,
                                @Path("userID") cardID: String?): Response<Card>

    companion object API{

        const val BASE_URL: String = "http://pokemoncardexchange.ddns.net/api/"

        object ApiURLCard {
            const val ALL_CARDS: String = "cards"
            const val USER_CARDS: String = "user/cards"
            const val FRIEND_CARDS: String = "user/friends/{userID}/cards"
            const val ADD_NEW_CARD: String = "user/cards/{cardID}"
        }
    }
}