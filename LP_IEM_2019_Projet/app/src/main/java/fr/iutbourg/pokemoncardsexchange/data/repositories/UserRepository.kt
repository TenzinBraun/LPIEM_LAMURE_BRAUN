package fr.iutbourg.pokemoncardsexchange.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.iutbourg.pokemoncardsexchange.data.model.UserResponse
import fr.iutbourg.pokemoncardsexchange.data.networking.datasource.UserDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private class UserRepositoryImpl : UserRepository {

    override fun register(
        email: String,
        password: String,
        name: String,
        firstname: String,
        scope: CoroutineScope
    ): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = UserDataSource.instance
            data.postValue(dataSource.register(email, password, name, firstname))
        }
        return data
    }

    override fun login(
        email: String,
        password: String,
        scope: CoroutineScope
    ): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = UserDataSource.instance
            data.postValue(dataSource.login(email, password))
        }
        return data
    }

    override fun auto_login(token: String, scope: CoroutineScope): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = UserDataSource.instance
            data.postValue(dataSource.auto_login(token))
        }
        return data
    }

    override fun getInfos(token: String, scope: CoroutineScope): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = UserDataSource.instance
            data.postValue(dataSource.getInfos(token))
        }
        return data
    }

    override fun update(
        email: String,
        name: String,
        firstname: String,
        scope: CoroutineScope
    ): LiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        scope.launch(Dispatchers.IO) {
            val dataSource = UserDataSource.instance
            data.postValue(dataSource.update(email, name, firstname))
        }
        return data
    }


}

interface UserRepository {
    fun register(email: String, password: String, name: String, firstname: String, scope: CoroutineScope): LiveData<UserResponse>
    fun login(email: String, password: String, scope: CoroutineScope): LiveData<UserResponse>
    fun auto_login(token: String, scope: CoroutineScope): LiveData<UserResponse>
    fun getInfos(token: String, scope: CoroutineScope): LiveData<UserResponse>
    fun update(email: String, name: String, firstname: String, scope: CoroutineScope): LiveData<UserResponse>


    companion object {
        /**
         * Singleton for [UserRepository]
         */

        val userRepositoryInstance: UserRepository by lazy {
            UserRepositoryImpl()
        }
    }
}