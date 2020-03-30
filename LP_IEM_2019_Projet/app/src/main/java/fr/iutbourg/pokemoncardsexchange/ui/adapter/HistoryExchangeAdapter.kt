package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.model.Propositions
import fr.iutbourg.pokemoncardsexchange.ui.widget.PokedexViewHolder
import fr.iutbourg.pokemoncardsexchange.ui.widget.PropositionsViewHolder

class HistoryExchangeAdapter :
    RecyclerView.Adapter<PropositionsViewHolder>() {

    private var propositions = emptyList<Propositions>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropositionsViewHolder {
        return PropositionsViewHolder.create(parent)
    }

    override fun getItemCount(): Int = propositions.size

    override fun onBindViewHolder(holder: PropositionsViewHolder, position: Int) {
        val item = propositions[position]
        holder.bindProposition(item)
    }

    fun submitList(propositions: List<Propositions>) {
        this.propositions = propositions
        notifyDataSetChanged()
    }
}

