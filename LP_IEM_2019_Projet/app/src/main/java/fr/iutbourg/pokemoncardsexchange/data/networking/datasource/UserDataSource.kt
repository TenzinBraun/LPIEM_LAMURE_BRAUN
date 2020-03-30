package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createUserApi
import fr.iutbourg.pokemoncardsexchange.data.model.UserResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.UserApi

private class UserDataSourceImpl(private val api: UserApi) : UserDataSource {

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        firstname: String
    ): UserResponse? {
        val response = api.register(email, password, name, firstname)
        return if (response.isSuccessful) {
            val user = response.body()
            UserResponse(user = user)
        } else {
            UserResponse(error = response.errorBody().toString())
        }
    }

    override suspend fun login(email: String, password: String): UserResponse? {
        val response = api.login(email, password)
        return if (response.isSuccessful) {
            val user = response.body()
            UserResponse(user = user)
        } else {
            UserResponse(error = response.errorBody().toString())
        }
    }

    override suspend fun autoLogin(token: String): UserResponse? {
        val response = api.autoLogin(token)
        return if (response.isSuccessful) {
            val user = response.body()
            UserResponse(user = user)
        } else {
            UserResponse(error = response.errorBody().toString())
        }
    }

    override suspend fun getInfos(token: String): UserResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun update(email: String, name: String, firstname: String): UserResponse? {
        TODO("Not yet implemented")
    }

}

interface UserDataSource {
    suspend fun register(
        email: String,
        password: String,
        name: String,
        firstname: String
    ): UserResponse?

    suspend fun login(email: String, password: String): UserResponse?
    suspend fun autoLogin(token: String): UserResponse?
    suspend fun getInfos(token: String): UserResponse?
    suspend fun update(email: String, name: String, firstname: String): UserResponse?

    companion object {
        /**
         * Singleton for the interface [UserResponse]
         */
        val instance: UserDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the userRepoInstance,
            // then, the reference will be stored in the value `userRepoInstance`
            UserDataSourceImpl(HttpClientManager.userInstance.createUserApi())
        }
    }
}