package fr.iutbourg.pokemoncardsexchange.activity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity(), CallBackScroll {


    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val pokedexFragment = PokedexFragment(this)
    private lateinit var alertDialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        configurePokedexFragment()
        researchFabMenuBar.setOnClickListener {
            buildSearchModal()
        }
    }

    private fun buildSearchModal() {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.create_search_modal, viewGroup, false)
        val builder = AlertDialog.Builder(this)
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


