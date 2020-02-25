package fr.iutbourg.pokemoncardsexchange.utils

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.get
import fr.iutbourg.pokemoncardsexchange.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.beans.Card
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexAdapter
import java.util.regex.Pattern

class PokemonFilter(pokedexActivity: PokedexActivity) {

    private var queryName: String = String()
    private val pokedexAdapter = PokedexAdapter.getInstance(pokedexActivity)
    private var filteredCardList = pokedexAdapter.pokedex
    private var energyNameList = listOf<String>()


    fun appendName(param: String): PokemonFilter {
        queryName = param
        return this
    }

    private fun filter(): PokemonFilter {

        when {
            queryName != "" -> {
                filteredCardList = filteredCardList.filter {
                    Pattern.compile(
                        Pattern.quote(queryName),
                        Pattern.CASE_INSENSITIVE
                    )
                        .matcher(it.name)
                        .find()
                }
            }
            energyNameList.isNotEmpty() -> {
                val tempArrayList = mutableListOf<Card>()
                filteredCardList.forEach { filterCard ->
                    if (energyNameList.any { energyName ->
                            Pattern.compile(
                                Pattern.quote(energyName),
                                Pattern.CASE_INSENSITIVE
                            )
                                .matcher(filterCard.types?.get(0))
                                .find()
                        })
                        tempArrayList.add(filterCard)

                }
                filteredCardList = tempArrayList
            }
        }
        return this
    }

    fun build() {
        filter()
        pokedexAdapter.submitFilterList(filteredCardList)
        filteredCardList = pokedexAdapter.pokedex
    }

    fun appendCheckbox(listOfEnergySelected: List<CheckBox>): PokemonFilter {
        energyNameList = listOfEnergySelected.filter { it.isChecked }.map {
            val viewGroup: ViewGroup = it.parent as ViewGroup
            val textView: TextView = viewGroup[1] as TextView
            textView.text.toString()
        }
        return this
    }
}

