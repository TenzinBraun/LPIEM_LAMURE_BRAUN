package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.repositories.FriendRepository

class FriendViewModel(private val repository: FriendRepository
): ViewModel(){

    fun getPeople(token: String) = repository.getPeople(viewModelScope, token)
    fun addFriends(userID: Int, token: String)
       = repository.addFriends(viewModelScope, userID, token)

    fun getFriends(token: String) = repository.getFriends(viewModelScope, token)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FriendViewModel(FriendRepository.friendRepoInstance) as T
        }
    }
}