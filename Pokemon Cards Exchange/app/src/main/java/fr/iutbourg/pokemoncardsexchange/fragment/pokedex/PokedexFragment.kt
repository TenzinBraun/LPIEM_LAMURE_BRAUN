package fr.iutbourg.pokemoncardsexchange.fragment.pokedex

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.activity.CallBackScroll
import fr.iutbourg.pokemoncardsexchange.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.activity.SingleCardPage
import fr.iutbourg.pokemoncardsexchange.beans.Card
import fr.iutbourg.pokemoncardsexchange.fragment.PokedexView
import fr.iutbourg.pokemoncardsexchange.presenter.PokedexPresenter
import kotlinx.android.synthetic.main.page_list_fragment.view.*
import kotlinx.android.synthetic.main.pokedex_fragment.view.*


class PokedexFragment(
    pokedexActivity: PokedexActivity
) : Fragment(), PokedexView {

    private val pokedexPresenter = PokedexPresenter()
    private lateinit var rootView: View
    private val pokedexAdapter = PokedexAdapter.getInstance(pokedexActivity)
    private val customScrollListener = CustomScrollListener(pokedexActivity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.pokedex_fragment, container, false)
        rootView.recyclerViewImage.layoutManager = GridLayoutManager(context, 3)
        rootView.recyclerViewImage.adapter = pokedexAdapter
        rootView.recyclerViewImage.addOnScrollListener(customScrollListener)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokedexPresenter.callAPIForPokedex(this)
    }

    override fun update(data: List<Card>) {
        pokedexAdapter.submitList(data)
    }
}

class PokedexAdapter(private val pokedexActivity: PokedexActivity) :
    RecyclerView.Adapter<PokedexAdapter.PokedexHolder>() {

    var pokedex = emptyList<Card>()
    private var temp = pokedex


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexHolder {
        val inflatedView: View = parent.inflate(R.layout.page_list_fragment, false)
        return PokedexHolder(inflatedView, pokedexActivity)
    }

    override fun getItemCount(): Int = temp.size

    override fun onBindViewHolder(holder: PokedexHolder, position: Int) {
        val itemPhoto = temp[position]
        holder.bindPhoto(itemPhoto)
    }

    fun submitList(pokedex: List<Card>) {
        this.temp = pokedex
        this.pokedex = pokedex
        notifyDataSetChanged()
    }

    fun submitFilterList(pokedex: List<Card>) {
        temp = pokedex
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
            Picasso.with(view.context).load(card.imageUrl).into(view.cardview[0] as ImageView)
        }
    }

    companion object InstanceAdapter {
        private var instance: PokedexAdapter? = null
        fun getInstance(pokedexActivity: PokedexActivity): PokedexAdapter {
            if (instance == null) {
                instance = PokedexAdapter(pokedexActivity)
            }
            return instance as PokedexAdapter
        }
    }
}

class CustomScrollListener(private val callBackScroll: CallBackScroll) :
    RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        when {
            dy > 0 -> callBackScroll.notiftyScroll(1)
            dy < 0 -> callBackScroll.notiftyScroll(2)
            else -> callBackScroll.notiftyScroll(0)
        }
    }
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}