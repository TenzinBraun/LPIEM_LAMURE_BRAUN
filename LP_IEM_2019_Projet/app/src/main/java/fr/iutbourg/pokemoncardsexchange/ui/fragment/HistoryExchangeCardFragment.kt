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
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.adapter.HistoryExchangeAdapter
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.ExchangeViewModel
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class HistoryExchangeCardFragment : Fragment() {

    private lateinit var exchangeViewModel: ExchangeViewModel
    private lateinit var historyExchangeAdapter: HistoryExchangeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            exchangeViewModel = ViewModelProvider(this, ExchangeViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.fragment_history_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recyclerViewImage.layoutManager = LinearLayoutManager(requireContext())
        view.recyclerViewImage.adapter = historyExchangeAdapter

        exchangeViewModel.getPropositions(
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!,
                PreferencesUtils.getInt(
                    "current_user_id",
                    -1,
                    requireContext()
                )
            ).observe(this) {
                it.propositions?.let { propositions ->
                    historyExchangeAdapter.submitList(propositions)
                }
            }
    }

}
