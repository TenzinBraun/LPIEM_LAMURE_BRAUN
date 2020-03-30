package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.model.PropositionsResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.ExchangeDataSource
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.PokedexDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private class ExchangeRepositoryImpl : ExchangeRepository {

    override fun getUserCard1(
        token: String,
        viewModelScope: CoroutineScope
    ): LiveData<PokedexResponse> {
        val data = MutableLiveData<PokedexResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getMyCards(token))
        }
        return data
    }

    override fun getUserCard2(
        token: String,
        userID: Int,
        viewModelScope: CoroutineScope
    ): LiveData<PokedexResponse> {
        val data = MutableLiveData<PokedexResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getUser2Card(token, userID))
        }
        return data
    }

    override fun submitExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            ExchangeDataSource.instance.submitExchange(userID, targetCardID, myCardID, token)
        }
    }

    override fun validateExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            ExchangeDataSource.instance.validateExchange(userID, targetCardID, myCardID, token)
        }
    }

    override fun cancelExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            ExchangeDataSource.instance.cancelExchange(userID, targetCardID, myCardID, token)
        }
    }

    override fun declineExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            ExchangeDataSource.instance.declineExchange(userID, targetCardID, myCardID, token)
        }
    }

    override fun getMyPropositions(token: String, userID: Int?, viewModelScope: CoroutineScope) {
        viewModelScope.launch(Dispatchers.IO) {
            ExchangeDataSource.instance.myPropositions(token, userID)
        }
    }

    override fun getPropositions(
        token: String,
        viewModelScope: CoroutineScope
    ): LiveData<PropositionsResponse> {
        val data = MutableLiveData<PropositionsResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(ExchangeDataSource.instance.getPropositionsMade(token))
        }
        return data
    }

}


interface ExchangeRepository {
    fun getUserCard1(token: String, viewModelScope: CoroutineScope): LiveData<PokedexResponse>
    fun getUserCard2(
        token: String,
        userID: Int,
        viewModelScope: CoroutineScope
    ): LiveData<PokedexResponse>

    fun submitExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    )

    fun validateExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    )

    fun cancelExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    )

    fun declineExchange(
        userID: Int?,
        targetCardID: String?,
        myCardID: String?,
        viewModelScope: CoroutineScope,
        token: String
    )

    fun getMyPropositions(
        token: String,
        userID: Int?,
        viewModelScope: CoroutineScope
    )

    fun getPropositions(
        token: String,
        viewModelScope: CoroutineScope
    ): LiveData<PropositionsResponse>


    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val exchangeRepoInstance: ExchangeRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            ExchangeRepositoryImpl()
        }
    }

}
