package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.Friend
import fr.iutbourg.pokemoncardsexchange.ui.widget.FriendViewHolder

class FriendAdapter : RecyclerView.Adapter<FriendViewHolder>() {

    private var friends = emptyList<Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder.create(parent)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val itemPhoto = friends[position]
        holder.bindID(itemPhoto)
    }

    fun submitList(pokedex: List<Friend>) {
        this.friends = pokedex
        notifyDataSetChanged()
    }
}
