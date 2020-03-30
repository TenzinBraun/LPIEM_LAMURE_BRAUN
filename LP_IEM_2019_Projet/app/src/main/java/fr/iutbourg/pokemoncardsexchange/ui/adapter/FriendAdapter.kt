package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.ui.activity.FriendActivity
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokedexListFragment
import fr.iutbourg.pokemoncardsexchange.ui.widget.AddFriendCallbackHandler
import fr.iutbourg.pokemoncardsexchange.ui.widget.FriendViewHolder
import kotlinx.android.synthetic.main.friend_view_holder.view.*

class FriendAdapter(
    private val addFriendCallbackHandler: AddFriendCallbackHandler
) : RecyclerView.Adapter<FriendViewHolder>() {

    private var friends = emptyList<User>()
    private var callbackHandler = addFriendCallbackHandler

        override

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder.create(parent, callbackHandler)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val user = friends[position]
        holder.bindID(user)
        holder.itemView.rootCard.setOnClickListener {
            val activity = it.context as FriendActivity
            val detailFriendFragment = PokedexListFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.friendContainer, detailFriendFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    fun submitList(pokedex: List<User>) {
        this.friends = pokedex
        notifyDataSetChanged()
    }
}
