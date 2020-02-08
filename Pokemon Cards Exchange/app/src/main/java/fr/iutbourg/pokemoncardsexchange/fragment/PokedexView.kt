package fr.iutbourg.pokemoncardsexchange.fragment

import fr.iutbourg.pokemoncardsexchange.beans.Card

interface PokedexView {
    fun update(data: List<Card>)
}
