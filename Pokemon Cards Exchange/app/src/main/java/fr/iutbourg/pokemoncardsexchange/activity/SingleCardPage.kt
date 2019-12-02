package fr.iutbourg.pokemoncardsexchange.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
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
        cardName.text = card.name
        Picasso.with(cardPicture.context).load(card.imageUrl).into(cardPicture)
    }

}
