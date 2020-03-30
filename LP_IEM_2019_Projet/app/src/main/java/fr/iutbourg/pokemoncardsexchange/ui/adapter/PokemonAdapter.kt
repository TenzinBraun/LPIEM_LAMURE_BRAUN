package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokemonCardCallBack
import fr.iutbourg.pokemoncardsexchange.ui.fragment.SelectCard
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokedexViewHolder

class PokedexAdapter(
    pokemonAddCardCallback: PokemonCardCallBack?,
    setCard: SelectCard?
) :
    RecyclerView.Adapter<PokedexViewHolder>() {

    private var pokedex = emptyList<Card>()
    private val callback = pokemonAddCardCallback
    private val callbackSetCard = setCard

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        return PokedexViewHolder.create(parent)
    }

    override fun getItemCount(): Int = pokedex.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val item = pokedex[position]
        holder.bindPhoto(item)
        holder.itemView.setOnLongClickListener {
            callback?.addCardToDB(item)
            true
        }
        holder.itemView.setOnClickListener{
            callback?.navigateToDetailFragment(item)
            callbackSetCard?.setCardSelect(item)

        }
        if(position%100 == 90){
            callback?.loadMoreCard(position)
        }
    }

    fun submitList(pokedex: List<Card>) {
        this.pokedex = pokedex
        notifyDataSetChanged()
    }

}