package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.UserResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.FriendDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private class FriendRepositoryImpl : FriendRepository{

    override fun getFriends(scope: CoroutineScope): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = FriendDataSource.instance
            data.postValue(dataSource.getFriends())
        }
        return data
    }
}

interface FriendRepository {

    fun getFriends(scope: CoroutineScope): LiveData<UserResponse>
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