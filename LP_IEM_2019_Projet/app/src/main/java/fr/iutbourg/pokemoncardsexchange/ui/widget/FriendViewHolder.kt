package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import kotlinx.android.synthetic.main.friend_view_holder.view.*

class FriendViewHolder private constructor(
    itemView: View,
    private val callbackHandler: AddFriendCallbackHandler
) : RecyclerView.ViewHolder(itemView) {

    fun bindID(friend: User) {
        val txt = itemView.rootCard[1] as TextView
        txt.text = friend.name.toString()
        itemView.addFriends.setOnClickListener {
            callbackHandler.addFriendsOnClick(friend.userID!!)
        }
    }
    companion object {
        /**
         * Create a new Instance of [PokedexViewHolder]
         */
        fun create(
            parent: ViewGroup, callbackHandler: AddFriendCallbackHandler
        ): FriendViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.friend_view_holder,
                parent,
                false
            )
            return FriendViewHolder(view, callbackHandler)
        }
    }

}
