package fr.iutbourg.pokemoncardsexchange.data.networking.datasource

import fr.iutbourg.pokemoncardsexchange.data.manager.HttpClientManager
import fr.iutbourg.pokemoncardsexchange.data.manager.createExchangeApi
import fr.iutbourg.pokemoncardsexchange.data.model.PropositionsResponse
import fr.iutbourg.pokemoncardsexchange.data.model.UserResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.api.ExchangeApi
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository

class ExchangeDataSourceImpl(private val exchangeAPI: ExchangeApi) : ExchangeDataSource {

    override suspend fun submitExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        token: String
    ) {
        exchangeAPI.exchange(token, userID, targetCardID, myCardID)
    }

    override suspend fun validateExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        token: String
    ) {
        exchangeAPI.validateExchange(token, userID, targetCardID, myCardID)
    }
    override suspend fun cancelExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        token: String
    ) {
        exchangeAPI.cancelExchange(token, userID, targetCardID, myCardID)
    }
    override suspend fun declineExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        token: String
    ) {
        exchangeAPI.declineExchange(token, userID, targetCardID, myCardID)
    }

    override suspend fun myPropositions(token: String, userID: Int?) {
        exchangeAPI.getMyPropositions(token, userID)
    }

    override suspend fun getPropositionsMade(token: String): PropositionsResponse {
        val response = exchangeAPI.getPropositionsMade(token)
        return if (response.isSuccessful) {
            val user = response.body()
            PropositionsResponse(user)
        } else {
            PropositionsResponse(error = 0)
        }
    }
}


interface ExchangeDataSource {
    suspend fun submitExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String)
    suspend fun validateExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String)
    suspend fun cancelExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String)
    suspend fun declineExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String)
    suspend fun myPropositions(token: String, userID: Int?)
    suspend fun getPropositionsMade(token: String): PropositionsResponse

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val instance: ExchangeDataSource by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            ExchangeDataSourceImpl(HttpClientManager.exchangeInstance.createExchangeApi())
        }
    }
}
