package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.Card
import fr.iutbourg.pokemoncardsexchange.ui.activity.MainActivity
import fr.iutbourg.pokemoncardsexchange.ui.adapter.PokedexAdapter
import kotlinx.android.synthetic.main.create_search_modal.*
import java.util.regex.Pattern

class PokemonFilter(private var filteredCardList: List<Card>, context: Context, private var adapter: PokedexAdapter, private var activity: FragmentActivity) :
    Dialog(context) {

    private var queryName: String = String()
    private var energyNameList = listOf<String>()
    private lateinit var inputPokemonName: String
    private var checkboxEnergy = mutableListOf<CheckBox>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.create_search_modal)
        buildSearchModal(cardList = filteredCardList)
        validateFilter.setOnClickListener {
            inputPokemonName = findViewById<EditText>(R.id.inputNamePokemon).text.toString()
            adapter.submitList(build())
            checkboxEnergy.map { checkBox -> if (checkBox.isChecked) checkBox.isChecked = false }
            this.dismiss()
        }
        setSizeforDialog(90, 1)
    }

    fun setSizeforDialog(percentWidth: Int, percentHeight: Int) {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = displayMetrics.widthPixels * percentWidth / 100
        when (percentHeight) {
            0 -> lp.height = WindowManager.LayoutParams.MATCH_PARENT
            1 -> lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            else -> lp.height = displayMetrics.heightPixels * percentHeight / 100
        }
        this.window?.attributes = lp
    }

    private fun buildSearchModal(cardList: List<Card>) {
//        val viewGroup = this.findViewById<ViewGroup>(android.R.id.content)
//        val dialogView =
//            LayoutInflater.from(context).inflate(R.layout.create_search_modal, viewGroup, false)
//        val validateFilter = dialogView.findViewById<Button>(R.id.validateFilter)

        buildCheckBoxList()


//        val builder = androidx.appcompat.app.AlertDialog.Builder(context!!)
//        builder.setView(dialogView)
//        window?.setBackgroundDrawableResource(android.R.color.transparent)
//        this.setCancelable(true)
//        this.setCanceledOnTouchOutside(true)
    }

    private fun buildCheckBoxList() {
        val grassCheckbox = findViewById<CheckBox>(R.id.checkboxPlante)
        checkboxEnergy.add(grassCheckbox)
        val fireCheckBox = findViewById<CheckBox>(R.id.checkboxFire)
        checkboxEnergy.add(fireCheckBox)
        val darkCheckBox = findViewById<CheckBox>(R.id.checkboxDark)
        checkboxEnergy.add(darkCheckBox)
        val fairyCheckBox = findViewById<CheckBox>(R.id.checkboxFairy)
        checkboxEnergy.add(fairyCheckBox)
        val waterCheckBox = findViewById<CheckBox>(R.id.checkboxWater)
        checkboxEnergy.add(waterCheckBox)
        val dragonCheckBox = findViewById<CheckBox>(R.id.checkboxDragon)
        checkboxEnergy.add(dragonCheckBox)
        val steelCheckBox = findViewById<CheckBox>(R.id.checkboxSteel)
        checkboxEnergy.add(steelCheckBox)
        val psyCheckBox = findViewById<CheckBox>(R.id.checkboxPsy)
        checkboxEnergy.add(psyCheckBox)
        val fightingCheckBox = findViewById<CheckBox>(R.id.checkboxFighting)
        checkboxEnergy.add(fightingCheckBox)
        val lightingCheckBox = findViewById<CheckBox>(R.id.checkboxElec)
        checkboxEnergy.add(lightingCheckBox)
        val incoloreCheckBox = findViewById<CheckBox>(R.id.checkboxIncolor)
        checkboxEnergy.add(incoloreCheckBox)
    }


    private fun appendName(param: String): PokemonFilter {
        queryName = param
        return this
    }

    private fun filter(): PokemonFilter {

        when {
            queryName != "" -> {
                filteredCardList = filteredCardList.filter {
                    Pattern.compile(
                        Pattern.quote(queryName),
                        Pattern.CASE_INSENSITIVE
                    )
                        .matcher(it.name)
                        .find()
                }
            }
            energyNameList.isNotEmpty() -> {
                val tempArrayList = mutableListOf<Card>()
                filteredCardList.forEach { filterCard ->
                    if (energyNameList.any { energyName ->
                            Pattern.compile(
                                Pattern.quote(energyName),
                                Pattern.CASE_INSENSITIVE
                            )
                                .matcher(filterCard.types?.get(0))
                                .find()
                        })
                        tempArrayList.add(filterCard)

                }
                filteredCardList = tempArrayList
            }
        }
        return this
    }

    fun build(): List<Card> {
        appendName(inputPokemonName)
        appendCheckbox(checkboxEnergy)
        filter()
        return filteredCardList
    }

    private fun appendCheckbox(listOfEnergySelected: List<CheckBox>): PokemonFilter {
        energyNameList = listOfEnergySelected.filter { it.isChecked }.map {
            val viewGroup: ViewGroup = it.parent as ViewGroup
            val textView: TextView = viewGroup[1] as TextView
            textView.text.toString()
        }
        return this
    }
}

