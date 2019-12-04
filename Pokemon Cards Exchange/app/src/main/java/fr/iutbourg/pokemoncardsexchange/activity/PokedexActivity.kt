package fr.iutbourg.pokemoncardsexchange.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.fragment.menu.BottomAppBarFragment
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    val bottomAppBarFragment = BottomAppBarFragment()
    val pokedexFragment = PokedexFragment(this, bottomAppBarFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        configureBottomAppBarFragment()
        configurePokedexFragment()
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(pokedexContainer.id, pokedexFragment)
        fragmentTransaction.commit()
    }

    private fun configureBottomAppBarFragment() {
        fragmentTransaction.add(bottomAppBar.id, bottomAppBarFragment)
    }
}
