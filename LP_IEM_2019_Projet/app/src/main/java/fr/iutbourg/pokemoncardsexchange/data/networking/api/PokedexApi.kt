package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PokedexApi {

    /**
     * Get all cards available on API
     * @return a [Response] of [Pokedex]
     */
    @GET(ApiURLCard.ALL_CARDS)
    suspend fun getAllCard2(): Response<Pokedex>
    companion object API{

        const val BASE_URL: String = "https://api.pokemontcg.io/v1/"

        object ApiURLCard {
            const val ALL_CARDS: String = "cards?subtype=EX"
        }
    }
}