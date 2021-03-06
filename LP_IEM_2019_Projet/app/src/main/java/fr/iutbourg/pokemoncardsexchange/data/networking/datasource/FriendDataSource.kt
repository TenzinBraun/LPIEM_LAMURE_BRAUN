package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createApi
import fr.iutbourg.pokemoncardsexchange.data.model.FriendResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.FriendApi
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository

private class FriendDataSourceImpl(private val api: FriendApi) : FriendDataSource {

    override suspend fun getFriends(): FriendResponse {
        val response = api.getAllFriends()
        return if (response.isSuccessful) {
            val poke = response.body()
            FriendResponse(poke)
        } else {
            FriendResponse(message = 0)
        }
    }
}

interface FriendDataSource {

    suspend fun getFriends(): FriendResponse

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val instance: FriendDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            FriendDataSourceImpl(HttpClientManager.friendInstance.createApi())
        }
    }
}