package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.repositories.ExchangeRepository

class ExchangeViewModel(private val repository: ExchangeRepository) : ViewModel() {
    fun getUserCard1(token: String) = repository.getUserCard1(token, viewModelScope)
    fun getUserCard2(token: String, userID: Int?) =
        repository.getUserCard2(token, userID!!, viewModelScope)

    fun submitExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String) =
        repository.submitExchange(userID, targetCardID, myCardID, viewModelScope, token)

    fun validateExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String) =
        repository.validateExchange(userID, targetCardID, myCardID, viewModelScope, token)

    fun cancelExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String) =
        repository.cancelExchange(userID, targetCardID, myCardID, viewModelScope, token)

    fun declineExchange(userID: Int?, targetCardID: String?, myCardID: String?, token: String) =
        repository.declineExchange(userID, targetCardID, myCardID, viewModelScope, token)

    fun getMyPropositions(token: String, userID: Int?) =
        repository.getMyPropositions(token, userID, viewModelScope)

    fun getPropositions(token: String, userID: Int?) =
        repository.getPropositions(token, viewModelScope)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ExchangeViewModel(ExchangeRepository.exchangeRepoInstance) as T
        }
    }

}
