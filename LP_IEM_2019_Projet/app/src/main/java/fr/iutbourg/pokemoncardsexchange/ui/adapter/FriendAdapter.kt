package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.ui.activity.FriendActivity
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokedexListFragment
import fr.iutbourg.pokemoncardsexchange.ui.widget.FriendViewHolder
import kotlinx.android.synthetic.main.friend_view_holder.view.*

class FriendAdapter : RecyclerView.Adapter<FriendViewHolder>() {

    private var friends = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder.create(parent)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val userID = friends[position]
        holder.bindID(userID)
        holder.itemView.rootCard.setOnClickListener{
            val activity =  it.context as FriendActivity
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
