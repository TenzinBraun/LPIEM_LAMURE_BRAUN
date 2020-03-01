package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokedexViewHolder

class PokedexAdapter :
    RecyclerView.Adapter<PokedexViewHolder>() {

    private var pokedex = emptyList<Card>()
    private var pokedexTemp = pokedex

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        return PokedexViewHolder.create(parent)
    }

    override fun getItemCount(): Int = pokedexTemp.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val itemPhoto = pokedexTemp[position]
        holder.bindPhoto(itemPhoto)
    }

    fun submitList(pokedex: List<Card>) {
        this.pokedexTemp = pokedex
        notifyDataSetChanged()
        pokedexTemp = this.pokedex
    }
}