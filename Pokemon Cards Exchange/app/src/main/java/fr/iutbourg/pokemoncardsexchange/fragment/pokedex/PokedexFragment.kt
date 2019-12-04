package fr.iutbourg.pokemoncardsexchange.fragment.pokedex

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.activity.SingleCardPage
import fr.iutbourg.pokemoncardsexchange.beans.Card
import fr.iutbourg.pokemoncardsexchange.presenter.PokedexPresenter
import kotlinx.android.synthetic.main.page_list_fragment.view.*
import kotlinx.android.synthetic.main.pokedex_fragment.view.*


class PokedexFragment(private val pokedexActivity: PokedexActivity) : Fragment(), PokedexView {

    private val pokedexPresenter = PokedexPresenter()
    private lateinit var rootView: View
    private val pokedexAdapter = PokedexAdapter(pokedexActivity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.pokedex_fragment, container, false)
        rootView.recyclerViewImage.layoutManager = GridLayoutManager(context,4)
        rootView.recyclerViewImage.adapter = pokedexAdapter

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokedexPresenter.callAPIForPokedex(this)
    }

    override fun update(data: List<Card>) {
        pokedexAdapter.sumbitList(data)
    }
}

class PokedexAdapter(private val pokedexActivity: PokedexActivity) : RecyclerView.Adapter<PokedexAdapter.PokedexHolder>() {

    private var pokedex = emptyList<Card>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexHolder {
        val inflatedView: View = parent.inflate(R.layout.page_list_fragment, false)
        return PokedexHolder(inflatedView, pokedexActivity)
    }

    override fun getItemCount(): Int = pokedex.size

    override fun onBindViewHolder(holder: PokedexHolder, position: Int) {
        val itemPhoto = pokedex[position]
        holder.bindPhoto(itemPhoto)
    }

    fun sumbitList(pokedex: List<Card>) {
        this.pokedex = pokedex
        notifyDataSetChanged()
    }

    class PokedexHolder(
        v: View,
        pokedexActivity: PokedexActivity
    ) : RecyclerView.ViewHolder(v) {

        private val view: View = v
        private var card: Card? = null
        init {
            v.setOnClickListener {
                val intent = Intent(pokedexActivity, SingleCardPage::class.java)
                intent.putExtra("card", card)
                pokedexActivity.startActivity(intent)
            }

        }
        fun bindPhoto(card: Card) {
            this.card = card
            Picasso.with(view.context).load(card.imageUrl).into(view.cardview.get(0) as ImageView)
        }
    }
}


interface PokedexView {
    fun update(data: List<Card>)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}