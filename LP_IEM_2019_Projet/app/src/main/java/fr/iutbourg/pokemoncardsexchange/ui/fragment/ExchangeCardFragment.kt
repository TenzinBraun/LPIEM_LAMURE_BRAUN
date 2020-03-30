package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.ExchangeViewModel
import kotlinx.android.synthetic.main.fragment_exchange_card.*
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class ExchangeCardFragment(private val userID: Int?) : Fragment(), SelectCard {

    private lateinit var exchangeViewModel: ExchangeViewModel
    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var cardsUser1: List<Card>
    private lateinit var cardsUser2: List<Card>
    private var cardUser1: Card? = null
    private var cardUser2: Card? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            exchangeViewModel = ViewModelProvider(this, ExchangeViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.fragment_exchange_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokedexAdapter = PokedexAdapter(null, this)

        view.recyclerViewImage.layoutManager = GridLayoutManager(activity, 3)
        view.recyclerViewImage.adapter = pokedexAdapter

        exchangeViewModel.getUserCard1(
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!
            )
            .observe(this) {
                it.pokedex?.cards?.let { cardList ->
                    this.cardsUser1 = cardList
                    pokedexAdapter.submitList(cardList)
                }
            }

        selectMyCard.setOnClickListener {
            selectMyCardContainer.visibility = GONE
            cardListContainer.visibility = VISIBLE
        }
        validateExchange.setOnClickListener {
            exchangeViewModel.submitExchange(
                userID,
                cardUser2!!.id,
                cardUser1!!.id,
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!
            )
            this.onDetach()
        }

    }

    override fun setCardSelect(card: Card) {
        if (cardUser1 == null) {
            cardUser1 = card
            exchangeViewModel.getUserCard2(
                    PreferencesUtils.getString(
                        "current_user_token",
                        "",
                        requireContext()
                    )!!,
                    userID
                )
                .observe(this) {
                    it.pokedex?.cards?.let { cardList ->
                        this.cardsUser2 = cardList
                        pokedexAdapter.submitList(cardList)
                    }
                }
            selectMyCardContainer.visibility = VISIBLE
            cardListContainer.visibility = GONE
            selectMyCard.text = getString(R.string.select_friend_card)
            labelSelectCard.text = getString(R.string.select_friend_card_label)


        } else if (cardUser2 == null) {
            cardUser2 = card
            selectMyCardContainer.visibility = VISIBLE
            cardListContainer.visibility = GONE
            selectMyCard.visibility = GONE
            validateExchange.visibility = VISIBLE

            labelSelectCard.text = getString(R.string.validate_exchange_label)
        }
    }


}

interface SelectCard {
    fun setCardSelect(card: Card)
}