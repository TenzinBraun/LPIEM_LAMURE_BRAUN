package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.PokedexViewModel
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokedexViewHolder

class PokedexAdapter(private val pokedexViewModel: PokedexViewModel) :
    RecyclerView.Adapter<PokedexViewHolder>() {

    private var pokedex = emptyList<Card>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        return PokedexViewHolder.create(parent)
    }

    override fun getItemCount(): Int = pokedex.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val item = pokedex[position]
        holder.bindPhoto(item)
        holder.itemView.setOnClickListener{
            pokedexViewModel.addCardToUserDB(it.context, item.id)
        }
    }

    fun submitList(pokedex: List<Card>) {
        this.pokedex = pokedex
        notifyDataSetChanged()
    }
}