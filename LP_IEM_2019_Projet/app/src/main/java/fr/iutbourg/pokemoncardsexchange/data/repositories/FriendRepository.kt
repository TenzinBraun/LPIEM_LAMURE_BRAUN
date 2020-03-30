package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.FriendsResponse
import fr.iutbourg.pokemoncardsexchange.data.model.MyFriendResponse
import fr.iutbourg.pokemoncardsexchange.data.model.RelationShipResponse
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.FriendDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private class FriendRepositoryImpl : FriendRepository{

    override fun getPeople(scope: CoroutineScope, token: String): LiveData<FriendsResponse> {
        val data = MutableLiveData<FriendsResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = FriendDataSource.instance
            data.postValue(dataSource.getPeople(token))
        }
        return data
    }

    override fun addFriends(scope: CoroutineScope, userID: Int, token: String): LiveData<RelationShipResponse> {
        val data = MutableLiveData<RelationShipResponse>()
        scope.launch(Dispatchers.IO) {
            data.postValue(FriendDataSource.instance.addFriends(userID, token))
        }
        return data
    }

    override fun getFriends(viewModelScope: CoroutineScope, token: String): LiveData<MyFriendResponse> {
        val data = MutableLiveData<MyFriendResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val dataSource = FriendDataSource.instance
            data.postValue(dataSource.getFriends(token))
        }
        return data
    }
}

interface FriendRepository {

    fun getPeople(scope: CoroutineScope, token: String): LiveData<FriendsResponse>
    fun addFriends(scope: CoroutineScope, userID: Int, token: String): LiveData<RelationShipResponse>
    fun getFriends(viewModelScope: CoroutineScope, token: String): LiveData<MyFriendResponse>

    companion object {
        /**
         * Singleton for the interface [FriendRepository]
         */
        val friendRepoInstance: FriendRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the pokedexRepoInstance,
            // then, the reference will be stored in the value `pokedexRepoInstance`
            FriendRepositoryImpl()
        }
    }
}