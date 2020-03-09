package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.PokedexDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private class PokedexRepositoryImpl : PokedexRepository{

    override fun getCards(scope: CoroutineScope): LiveData<PokedexResponse>{
        val data = MutableLiveData<PokedexResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getCards())
        }
        return data
    }

    override fun getUserCards(
        viewModelScope: CoroutineScope,
        token: String
    ): LiveData<PokedexResponse> {
        val data = MutableLiveData<PokedexResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = PokedexDataSource.instance
            data.postValue(dataSource.getUserCards(token))
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
}

interface PokedexRepository {

    fun getCards(scope: CoroutineScope): LiveData<PokedexResponse>
    fun getUserCards(viewModelScope: CoroutineScope, token: String): LiveData<PokedexResponse>
    fun getCardsForFriend(viewModelScope: CoroutineScope,
                          token: String,
                          userID: Int): LiveData<PokedexResponse>

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