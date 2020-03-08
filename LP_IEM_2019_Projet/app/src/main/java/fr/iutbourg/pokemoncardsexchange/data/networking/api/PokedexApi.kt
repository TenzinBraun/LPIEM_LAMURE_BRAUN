package fr.iutbourg.pokemoncardsexchange.data.networking.api

import android.content.SharedPreferences
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils.Companion.getString
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PokedexApi {

    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.ALL_CARDS)
    suspend fun getAllCard2(): Response<Pokedex>

    @GET()
    suspend fun getAllCardForID(@Header("token") token: String): Response<Pokedex>

    companion object API{

        const val BASE_URL: String = "http://pokemoncardexchange.ddns.net/api/"

        object ApiURLCard {
            const val ALL_CARDS: String = "cards"
            const val USER_CARDS: String = "user/cards"
        }
    }
}