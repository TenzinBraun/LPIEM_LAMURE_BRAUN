package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.repositories.FriendRepository

class FriendViewModel(repository: FriendRepository
): ViewModel(){

    val friends = repository.getFriends(viewModelScope)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FriendViewModel(FriendRepository.friendRepoInstance) as T
        }
    }
}