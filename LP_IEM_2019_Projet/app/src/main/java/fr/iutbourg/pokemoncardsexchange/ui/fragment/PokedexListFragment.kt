package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.PokedexViewModel
import fr.iutbourg.pokemoncardsexchange.ui.widget.CallBackScroll
import fr.iutbourg.pokemoncardsexchange.ui.widget.CustomScrollListener
import kotlinx.android.synthetic.main.activity_pokedex.*
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class PokedexListFragment : Fragment(), CallBackScroll {

    private lateinit var pokemonViewModel: PokedexViewModel
    private lateinit var pokemonAdapter: PokedexAdapter
    private lateinit var customScrollListener: CustomScrollListener
    private lateinit var cardList: List<Card>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            pokemonViewModel = ViewModelProvider(this, PokedexViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokedex_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // We need to inject the OnCharacterClickListener in the constructor of the adapter

        customScrollListener = CustomScrollListener(this)
        pokemonAdapter = PokedexAdapter()

        view.recyclerViewImage.layoutManager = GridLayoutManager(activity, 3)
        view.recyclerViewImage.adapter = pokemonAdapter
        view.recyclerViewImage.addOnScrollListener(customScrollListener)

        pokemonViewModel.pokedex.observe(this) {
            it.pokedex?.cards?.let { cardList ->
                this.cardList = cardList
                pokemonAdapter.submitList(cardList)
            }
        }
        activity?.researchFabMenuBar?.setOnClickListener {
            pokemonViewModel.showFilterPokemonDialog(cardList, pokemonAdapter, context!!,activity!!)
        }
    }

    private fun submitResponseCode(responseCode: Int) {
        when (responseCode) {
            1 -> {
                activity?.myBottomAppBar?.performHide()
                activity?.researchFabMenuBar?.hide()
            }
            2 -> {
                activity?.myBottomAppBar?.performShow()
                activity?.researchFabMenuBar?.show()
            }
        }
    }
    override fun notiftyScroll(responseCode: Int) {
        submitResponseCode(responseCode)
    }

}