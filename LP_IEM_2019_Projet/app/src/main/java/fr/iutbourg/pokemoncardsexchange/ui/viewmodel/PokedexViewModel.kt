package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokemonFilter

class PokedexViewModel(private val
    repository: PokedexRepository
): ViewModel(){

    fun getUserCards(token: String): LiveData<PokedexResponse> {
        return repository.getUserCards(viewModelScope, token)
    }

    val pokedex = repository.getCards(viewModelScope)

    fun showFilterPokemonDialog(cards: List<Card>, adapter: PokedexAdapter, context: Context, activity: FragmentActivity) {
        val pokedexFilter = PokemonFilter(cards, context, adapter, activity)
        pokedexFilter.show()
    }

    fun getFriendCards(token: String, userID: Int): LiveData<PokedexResponse> {
        return repository.getCardsForFriend(viewModelScope, token, userID)
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokedexViewModel(PokedexRepository.pokedexRepoInstance) as T
        }
    }
}

typealias OnSuccess<T> = (T) -> Unit