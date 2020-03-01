package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
    private lateinit var alertDialog: Dialog
    private lateinit var inputPokemonName: String
    private var checkboxEnergy = mutableListOf<CheckBox>()
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

                pokemonAdapter.submitList(cardList)
            }
        }
        activity?.researchFabMenuBar?.setOnClickListener {
            buildSearchModal(cardList)
        }
    }

    private fun buildSearchModal(cardList: List<Card>) {
        val viewGroup = activity?.findViewById<ViewGroup>(android.R.id.content)
        val dialogView =
            LayoutInflater.from(activity).inflate(R.layout.create_search_modal, viewGroup, false)
        val builder = AlertDialog.Builder(context!!)
        val validateFilter = dialogView.findViewById<Button>(R.id.validateFilter)

        buildCheckBoxList(dialogView)

        validateFilter.setOnClickListener {
            inputPokemonName = dialogView.findViewById<EditText>(R.id.inputNamePokemon).text.toString()
            pokemonViewModel.filterPokemonList(cardList, checkboxEnergy, inputPokemonName).let { cardList -> pokemonAdapter.submitList(cardList) }
            alertDialog.dismiss()
        }
        builder.setView(dialogView)
        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(true)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    private fun buildCheckBoxList(dialogView: View) {
        val grassCheckbox = dialogView.findViewById<CheckBox>(R.id.checkboxPlante)
        checkboxEnergy.add(grassCheckbox)
        val fireCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxFire)
        checkboxEnergy.add(fireCheckBox)
        val darkCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxDark)
        checkboxEnergy.add(darkCheckBox)
        val fairyCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxFairy)
        checkboxEnergy.add(fairyCheckBox)
        val waterCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxWater)
        checkboxEnergy.add(waterCheckBox)
        val dragonCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxDragon)
        checkboxEnergy.add(dragonCheckBox)
        val steelCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxSteel)
        checkboxEnergy.add(steelCheckBox)
        val psyCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxPsy)
        checkboxEnergy.add(psyCheckBox)
        val fightingCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxFighting)
        checkboxEnergy.add(fightingCheckBox)
        val lightingCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxElec)
        checkboxEnergy.add(lightingCheckBox)
        val incoloreCheckBox = dialogView.findViewById<CheckBox>(R.id.checkboxIncolor)
        checkboxEnergy.add(incoloreCheckBox)
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