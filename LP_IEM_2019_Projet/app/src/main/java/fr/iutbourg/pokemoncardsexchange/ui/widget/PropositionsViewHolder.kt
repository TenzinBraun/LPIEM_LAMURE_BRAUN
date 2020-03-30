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
import fr.iutbourg.pokemoncardsexchange.data.model.Propositions
import kotlinx.android.synthetic.main.card_view_holder.view.*
import kotlinx.android.synthetic.main.proposition_view_holder.view.*

class PropositionsViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindProposition(propositions: Propositions) {
        Picasso.with(itemView.context).load(propositions.otherCard.imageUrl).into(itemView.otherUserCard)
        Picasso.with(itemView.context).load(propositions.myCard.imageUrl).into(itemView.myCardSent)
    }

    companion object {
        /**
         * Create a new Instance of [FriendViewHolder]
         */
        fun create(parent: ViewGroup): PropositionsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.proposition_view_holder,
                parent,
                false
            )
            return PropositionsViewHolder(view)
        }
    }
}