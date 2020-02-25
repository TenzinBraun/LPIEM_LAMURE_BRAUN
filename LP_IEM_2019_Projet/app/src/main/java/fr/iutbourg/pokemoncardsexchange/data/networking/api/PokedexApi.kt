package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

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
            const val CARD_ID: String = ALL_CARDS + "id"
            const val CARD_NATIONAL_POKEDEX_NUMBER: String = "nationalPokedexNumber"
            const val CARD_TYPE: String = "types"
            const val CARD_SUBTYPE: String = "subtype"
            const val CARD_SUPERTYPE: String = "supertype"
            const val CARD_HEALTH_POINT: String = "hp"
        }

        object ApiURLSets {
            const val ALL_SETS: String = "sets"
            const val SETS_NAME: String = "name"
            const val SETS_PAGE: String = "page"
            const val SETS_PAGE_SIZE: String = "pageSize"
        }
    }
}