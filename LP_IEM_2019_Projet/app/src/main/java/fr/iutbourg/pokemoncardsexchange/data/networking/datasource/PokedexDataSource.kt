package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createPokedexApi
import fr.iutbourg.pokemoncardsexchange.data.model.CardResponse
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.PokedexApi
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository

//singleton car ne conserve pas les données et s'appuie sur retrofit
private class PokedexDataSourceImpl(private val api: PokedexApi) : PokedexDataSource {

    override suspend fun getUserCards(token: String, page: Int): PokedexResponse {
        val response = api.getUserCards(token, page)
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }

    override suspend fun getCardsForFriend(token: String, userID: Int): PokedexResponse? {
        val response = api.getCardForFriend(token, userID)
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }

    override suspend fun addCardToUserDB(token: String, cardID: String?): CardResponse {
        val response = api.addCardToUserDB(token, cardID)
        return if (response.isSuccessful) {
            val card = response.body()
            CardResponse(card)
        } else {
            CardResponse(error = 1)
        }
    }

    override suspend fun getMyCards(token: String): PokedexResponse? {
        val response = api.getMyCards(token)
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }

    override suspend fun getUser2Card(token: String, userID: Int): PokedexResponse? {
        val response = api.getCardForFriend(token, userID)
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }
}

interface PokedexDataSource {
    suspend fun getUserCards(token: String, page: Int): PokedexResponse
    suspend fun getCardsForFriend(token: String, userID: Int): PokedexResponse?
    suspend fun addCardToUserDB(token: String, cardID: String?): CardResponse
    suspend fun getMyCards(token: String): PokedexResponse?
    suspend fun getUser2Card(token: String, userID: Int): PokedexResponse?

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val instance: PokedexDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            PokedexDataSourceImpl(HttpClientManager.pokedexInstance.createPokedexApi())
        }
    }
}