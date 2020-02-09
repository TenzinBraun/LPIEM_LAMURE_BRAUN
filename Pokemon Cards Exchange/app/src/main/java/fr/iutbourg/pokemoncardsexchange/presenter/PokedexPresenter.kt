package fr.iutbourg.pokemoncardsexchange.presenter

import fr.iutbourg.pokemoncardsexchange.asynchtask.pokedex.PokedexAsyncTask
import fr.iutbourg.pokemoncardsexchange.fragment.PokedexView

class PokedexPresenter {

    private val pokedexAsyncTask = PokedexAsyncTask()

    fun callAPIForPokedex(view: PokedexView) {
        pokedexAsyncTask.getAllCardFromAPI(view::update)
    }



}