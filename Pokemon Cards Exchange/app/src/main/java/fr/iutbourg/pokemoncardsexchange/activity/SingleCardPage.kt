package fr.iutbourg.pokemoncardsexchange.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.beans.Card

import kotlinx.android.synthetic.main.activity_single_card_page.*

class SingleCardPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_card_page)
        val card: Card = intent.getSerializableExtra("card") as Card
        val cardColorType = card.types
//        cardName.text = card.name
//        cardHP.text = (card.hp + "HP")
//        if (card.types!!.size > 1) {
//            cardTypes.text = (card.types!!.get(0) + " / " + card.types!!.get(1))
//        } else {
//            cardTypes.text = card.types!!.get(0)
//        }

        Picasso.with(cardPicture.context).load(card.imageUrlHiRes).into(cardPicture)
        for (value in PokemonColor.values()) {
            val string = cardColorType!![0].toUpperCase()
            if (string == "NORMAL" || string == "LIGHTNING") {

                rootview.setBackgroundColor(Color.parseColor(PokemonColor.valueOf(string).hex))
//                cardName.setTextColor(Color.parseColor("#000000"))
//                cardTypes.setTextColor(Color.parseColor("#000000"))
//                cardHP.setTextColor(Color.parseColor("#000000"))
            } else if (string == value.name && string != "NORMAL" && string != "LIGHTNING") {
                rootview.setBackgroundColor(Color.parseColor(PokemonColor.valueOf(string).hex))
//                cardName.setTextColor(Color.parseColor("#FFFFFF"))
//                cardTypes.setTextColor(Color.parseColor("#FFFFFF"))
//                cardHP.setTextColor(Color.parseColor("#FFFFFF"))
            }

        }
    }

}

enum class PokemonColor(val hex: String) {
    NORMAL("#22A8A77A"),
    FIRE("#22EE8130"),
    WATER("#226390F0"),
    LIGHTNING("#22F7D02C"),
    GRASS("#227AC74C"),
    ICE("#2296D9D6"),
    FIGHTING("#22C22E28"),
    POISON("#22A33EA1"),
    GROUND("#22E2BF65"),
    FLYING("#22A98FF3"),
    PSYCHIC("#225A3954"),
    BUG("#22A6B91A"),
    ROCK("#22B6A136"),
    GHOST("#22735797"),
    DRAGON("#22614A25"),
    DARKNESS("#222D2E2B"),
    METAL("#22B7B7CE"),
    FAIRY("#22D685AD")
}
