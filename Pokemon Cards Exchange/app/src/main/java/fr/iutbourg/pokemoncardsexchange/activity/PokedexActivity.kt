package fr.iutbourg.pokemoncardsexchange.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.fragment.pokedex.PokedexFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity() {

    private val fragmentManager =  supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        val pokedexFragment = PokedexFragment(this)
        fragmentTransaction.add(pokedexContainer.id, pokedexFragment)
        fragmentTransaction.commit()

    }
}
