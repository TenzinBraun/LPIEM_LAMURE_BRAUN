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
    private var queryCheckBox = mutableListOf<CheckBox>()
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
                var card: String
                val tempArrayList = mutableListOf<Card>()
                for (it: Card in filteredCardList) {
                    card = it.types?.get(0)!!

                    if (energyNameList.any { string ->
                            Pattern.compile(
                                Pattern.quote(string),
                                Pattern.CASE_INSENSITIVE
                            )
                                .matcher(card)
                                .find()
                        })
                        tempArrayList.add(it)

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
        queryCheckBox = listOfEnergySelected.filter { it.isChecked } as MutableList<CheckBox>
        energyNameList = queryCheckBox.map {
            val viewGroup: ViewGroup = it.parent as ViewGroup
            val textView: TextView = viewGroup[1] as TextView
            textView.text.toString()
        }
        queryCheckBox.clear()
        return this
    }

}

