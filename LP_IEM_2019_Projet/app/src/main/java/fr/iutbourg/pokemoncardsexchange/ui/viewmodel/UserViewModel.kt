package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.model.UserResponse
import fr.iutbourg.pokemoncardsexchange.data.repositories.UserRepository

class UserViewModel (
    private val repository: UserRepository
): ViewModel() {

    fun register(email: String, password: String, name: String, firstname: String): LiveData<UserResponse> {
        return repository.register(email, password, name, firstname, viewModelScope)
    }

    fun login(email: String, password: String): LiveData<UserResponse> {
        return repository.login(email, password, viewModelScope)
    }

    fun autoLogin(token: String): LiveData<UserResponse?> {
        return repository.autoLogin(token, viewModelScope)
    }

    fun getInfos(token: String): LiveData<UserResponse> {
        return repository.getInfos(token, viewModelScope)
    }

    fun update(email: String, name: String, firstname: String): LiveData<UserResponse> {
        return repository.update(email, name, firstname, viewModelScope)
    }


    companion object Factory: ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(UserRepository.userRepositoryInstance) as T
        }
    }
}