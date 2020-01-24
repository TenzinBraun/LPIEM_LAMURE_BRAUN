package fr.iutbourg.pokemoncardsexchange.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity(), CallBackScroll {


    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val pokedexFragment = PokedexFragment(this,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        configurePokedexFragment()
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(pokedexContainer.id, pokedexFragment)
        fragmentTransaction.commit()
    }


    private fun submitResponseCode(responseCode: Int) {
        when {
            responseCode == 1 -> {
                myBottomAppBar.performHide()
                researchFabMenuBar.hide()
            }
            responseCode == 2 -> {
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
