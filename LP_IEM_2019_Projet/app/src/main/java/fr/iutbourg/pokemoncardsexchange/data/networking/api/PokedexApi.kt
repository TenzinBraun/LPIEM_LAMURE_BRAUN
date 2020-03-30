package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Response
import retrofit2.http.*

interface PokedexApi {

    /**
     * Get all cards for User
     * @param token [Header] to get User cards
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.ALL_CARDS)
    suspend fun getUserCards(
        @Header("token") token: String,
        @Query("page") page: Int
    ): Response<Pokedex>

    /**
     * Get all cards for Friend ID
     * @param token [String] and userID [Path] [Int]
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.FRIEND_CARDS)
    suspend fun getCardForFriend(
        @Header("token") token: String,
        @Path("userID") userID: Int
    ): Response<Pokedex>

    @POST(ApiURLCard.ADD_NEW_CARD)
    suspend fun addCardToUserDB(
        @Header("token") token: String,
        @Path("cardID") cardID: String?
    ): Response<Card>

    @GET(ApiURLCard.USER_CARDS)
    suspend fun getMyCards(@Header("token") token: String): Response<Pokedex>

    companion object API {

        const val BASE_URL: String = "http://pokemontcg.ddns.net/api/"

        object ApiURLCard {
            const val ALL_CARDS: String = "pokedex"
            const val USER_CARDS: String = "user/cards"
            const val FRIEND_CARDS: String = "user/friends/{userID}/cards"
            const val ADD_NEW_CARD: String = "user/cards/{cardID}"
        }
    }
}