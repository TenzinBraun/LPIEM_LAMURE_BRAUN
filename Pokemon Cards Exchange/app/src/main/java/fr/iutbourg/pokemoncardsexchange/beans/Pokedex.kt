package fr.iutbourg.pokemoncardsexchange.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Pokedex: Serializable {
    @SerializedName("cards")
    @Expose
    private var cards: List<Card>? = null

    fun getCards(): List<Card>? {
        return cards
    }

    fun setCards(cards: List<Card>) {
        this.cards = cards
    }
}