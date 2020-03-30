package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createFriendApi
import fr.iutbourg.pokemoncardsexchange.data.model.FriendsResponse
import fr.iutbourg.pokemoncardsexchange.data.model.MyFriendResponse
import fr.iutbourg.pokemoncardsexchange.data.model.RelationShipResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.FriendApi
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository

private class FriendDataSourceImpl(private val api: FriendApi) : FriendDataSource {

    override suspend fun getPeople(token: String): FriendsResponse {
        val response = api.getPeople(token)
        return if (response.isSuccessful) {
            val friends = response.body()
            FriendsResponse(friends)
        } else {
            FriendsResponse(error = 1)
        }
    }

    override suspend fun addFriends(userID: Int, token: String): RelationShipResponse {
        val response = api.addFriends(userID, userID, token)
        return if (response.isSuccessful) {
            val relationShip = response.body()
            RelationShipResponse(relationShip)
        } else {
            RelationShipResponse(error = response.errorBody().toString())
        }
    }

    override suspend fun getFriends(token: String): MyFriendResponse? {
        val response = api.getFriends(token)
        return if (response.isSuccessful) {
            val friends = response.body()
            MyFriendResponse(friends)
        } else {
            MyFriendResponse(error = 1)
        }
    }
}

interface FriendDataSource {

    suspend fun getPeople(token: String): FriendsResponse
    suspend fun addFriends(userID: Int, token: String): RelationShipResponse
    suspend fun getFriends(token: String): MyFriendResponse?

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val instance: FriendDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            FriendDataSourceImpl(HttpClientManager.friendInstance.createFriendApi())
        }
    }
}