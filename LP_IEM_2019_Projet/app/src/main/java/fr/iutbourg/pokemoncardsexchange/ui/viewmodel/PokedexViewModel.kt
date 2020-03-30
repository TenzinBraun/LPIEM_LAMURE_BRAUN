package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.model.CardResponse
import fr.iutbourg.pokemoncardsexchange.data.model.PokedexResponse
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import fr.iutbourg.pokemoncardsexchange.ui.dialog.PokemonFilter

class PokedexViewModel(private val
    repository: PokedexRepository
): ViewModel(){

    fun getUserCards(token: String, page: Int): LiveData<PokedexResponse> {
        return repository.getUserCards(viewModelScope, token, page)
    }


    fun showFilterPokemonDialog(cards: List<Card>, adapter: PokedexAdapter, context: Context, activity: FragmentActivity) {
        val pokedexFilter =
            PokemonFilter(
                cards,
                context,
                adapter,
                activity
            )
        pokedexFilter.show()
    }
    fun addCardToUserDB(context: Context, cardID: String?): LiveData<CardResponse> {
        return repository.addCardToUserDB(viewModelScope, PreferencesUtils.getString("current_user_token","token",context)!!, cardID)
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokedexViewModel(PokedexRepository.pokedexRepoInstance) as T
        }
    }
}