package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import kotlinx.android.synthetic.main.card_view_holder.view.*

class PokedexViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindPhoto(card: Card) {
        Picasso.with(itemView.context).load(card.imageUrl).into(itemView.cardview[0] as ImageView)
    }

    companion object {
        /**
         * Create a new Instance of [PokedexViewHolder]
         */
        fun create(parent: ViewGroup): PokedexViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_holder,
                parent,
                false
            )
            return PokedexViewHolder(view)
        }
    }
}