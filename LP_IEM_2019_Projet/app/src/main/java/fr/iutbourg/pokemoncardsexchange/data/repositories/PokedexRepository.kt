package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.CardResponse
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.PokedexDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private class PokedexRepositoryImpl : PokedexRepository {


    override fun getUserCards(
        viewModelScope: CoroutineScope,
        token: String,
        page: Int
    ): LiveData<PokedexResponse> {
        val data = MutableLiveData<PokedexResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getUserCards(token, page))
        }
        return data
    }

    override fun getCardsForFriend(
        viewModelScope: CoroutineScope,
        token: String,
        userID: Int
    ): LiveData<PokedexResponse> {
        val data = MutableLiveData<PokedexResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getCardsForFriend(token, userID))
        }
        return data
    }

    override fun addCardToUserDB(
        viewModelScope: CoroutineScope,
        token: String,
        cardID: String?
    ): LiveData<CardResponse> {
        val data = MutableLiveData<CardResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.addCardToUserDB(token, cardID))
        }
        return data
    }
}

interface PokedexRepository {
    fun getUserCards(
        viewModelScope: CoroutineScope,
        token: String,
        page: Int
    ): LiveData<PokedexResponse>

    fun getCardsForFriend(
        viewModelScope: CoroutineScope,
        token: String,
        userID: Int
    ): LiveData<PokedexResponse>

    fun addCardToUserDB(
        viewModelScope: CoroutineScope,
        token: String,
        cardID: String?
    ): LiveData<CardResponse>

    companion object {
        /**
         * Singleton for the interface [PokedexRepository]
         */
        val pokedexRepoInstance: PokedexRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            PokedexRepositoryImpl()
        }
    }
}