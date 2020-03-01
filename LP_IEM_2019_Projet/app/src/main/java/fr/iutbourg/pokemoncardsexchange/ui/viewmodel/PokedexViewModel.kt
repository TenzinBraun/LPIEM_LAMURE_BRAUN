package fr.iutbourg.pokemoncardsexchange.ui.viewmodel

import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.repositories.PokedexRepository
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokemonFilter

class PokedexViewModel(
    repository: PokedexRepository
): ViewModel(){

    val pokedex = repository.getCards(viewModelScope)


    fun filterPokemonList(cards: List<Card>, listOfCheckBox: List<CheckBox>, inputName: String): List<Card> {
        val pokedexFilter = PokemonFilter(cards)
        pokedexFilter.appendCheckbox(listOfCheckBox)
            .appendName(inputName)
        return pokedexFilter.build()
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokedexViewModel(PokedexRepository.pokedexRepoInstance) as T
        }
    }
}

typealias OnSuccess<T> = (T) -> Unit