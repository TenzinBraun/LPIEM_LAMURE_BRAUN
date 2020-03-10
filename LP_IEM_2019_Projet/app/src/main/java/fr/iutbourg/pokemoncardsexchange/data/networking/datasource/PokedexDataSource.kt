package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createApi
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.PokedexApi
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository

//singleton car ne conserve pas les données et s'appuie sur retrofit
private class PokedexDataSourceImpl(private val api: PokedexApi) : PokedexDataSource {

    override suspend fun getCards(): PokedexResponse {
        val response = api.getAllCard2()
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }

    override suspend fun getCardsForID(token: String): PokedexResponse {
        val response = api.getAllCardForID(token)
        return if (response.isSuccessful) {
            val poke = response.body()
            PokedexResponse(poke)
        } else {
            PokedexResponse(error = 1)
        }
    }
}

interface PokedexDataSource {

    suspend fun getCards(): PokedexResponse
    suspend fun getCardsForID(token: String): PokedexResponse

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val instance: PokedexDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            PokedexDataSourceImpl(HttpClientManager.pokedexInstance.createApi())
        }
    }
}