package fr.iutbourg.pokemoncardsexchange.utils

import android.widget.CheckBox
import fr.iutbourg.pokemoncardsexchange.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexAdapter
import java.util.regex.Pattern

class PokemonFilter(pokedexActivity: PokedexActivity) {

    private var queryName: String = String()
    private var queryCheckBox = mutableListOf<CheckBox>()
    private val pokedexAdapter = PokedexAdapter.getInstance(pokedexActivity)
    private var filteredCardList = pokedexAdapter.pokedex


    fun appendName(param: String): PokemonFilter {
        queryName = param
        return this
    }

    private fun filter(): PokemonFilter {
        filteredCardList = filteredCardList.filter {
            Pattern.compile(Pattern.quote(queryName),
            Pattern.CASE_INSENSITIVE)
                .matcher(it.name)
                .find()
        }
        return this
    }

    fun build() {
        filter()
        pokedexAdapter.submitFilterList(filteredCardList)
        filteredCardList = pokedexAdapter.pokedex
    }

    fun appendCheckbox(listOfEnergySelected: List<CheckBox>): PokemonFilter {
        queryCheckBox = listOfEnergySelected.filter { it.isChecked } as MutableList<CheckBox>
        return this
    }

}

