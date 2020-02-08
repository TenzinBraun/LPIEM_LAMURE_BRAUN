package fr.iutbourg.pokemoncardsexchange.activity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexFragment
import fr.iutbourg.pokemoncardsexchange.utils.PokemonFilter
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity(), CallBackScroll {


    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val pokedexFragment = PokedexFragment(this)
    private lateinit var alertDialog: Dialog
    private lateinit var inputPokemonName: String
    private var checkboxEnergy = mutableListOf<CheckBox>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        alertDialog = Dialog(this)
        configurePokedexFragment()
        researchFabMenuBar.setOnClickListener {
            buildSearchModal()
        }
    }

    private fun buildFilterContent() {
        val pokemonFilter = PokemonFilter(this)
        pokemonFilter.appendName(inputPokemonName)
            .appendCheckbox(getListOfEnergySelected(checkboxEnergy))
            .build()
    }

    private fun getListOfEnergySelected(checkboxEnergy: MutableList<CheckBox>): List<CheckBox> {
        return checkboxEnergy.filter {
            it.isChecked
        }
    }

    private fun buildSearchModal() {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.create_search_modal, viewGroup, false)
        val builder = AlertDialog.Builder(this)
        val validateFilter = dialogView.findViewById<Button>(R.id.validateFilter)


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

        validateFilter.setOnClickListener {
            inputPokemonName = dialogView.findViewById<EditText>(R.id.inputNamePokemon).text.toString()
            buildFilterContent()
            alertDialog.dismiss()
        }

        builder.setView(dialogView)
        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(true)
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(pokedexContainer.id, pokedexFragment)
        fragmentTransaction.commit()
    }


    private fun submitResponseCode(responseCode: Int) {
        when (responseCode) {
            1 -> {
                myBottomAppBar.performHide()
                researchFabMenuBar.hide()
            }
            2 -> {
                myBottomAppBar.performShow()
                researchFabMenuBar.show()
            }
        }
    }

    override fun notiftyScroll(respondeCode: Int) {
        submitResponseCode(responseCode = respondeCode)
    }
}


interface CallBackScroll {

    fun notiftyScroll(respondeCode: Int)
}


