package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.model.Pokedex
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository
import kotlinx.coroutines.launch

class PokedexViewModel(
    repository: PokedexRepository
): ViewModel(){

    val pokedex = repository.getCards(viewModelScope)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokedexViewModel(PokedexRepository.pokedexRepoInstance) as T
        }
    }
}

typealias OnSuccess<T> = (T) -> Unit