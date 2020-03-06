package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.model.Friend
import kotlinx.android.synthetic.main.card_view_holder.view.*
import kotlinx.android.synthetic.main.friend_view_holder.view.*

class FriendViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindID(friend: Friend) {
        val txt : TextView = itemView.cardview[1] as TextView
        txt.text = friend.id.toString()
    }

    companion object {
        /**
         * Create a new Instance of [PokedexViewHolder]
         */
        fun create(parent: ViewGroup): FriendViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.friend_view_holder,
                parent,
                false
            )
            return FriendViewHolder(view)
        }
    }

}
