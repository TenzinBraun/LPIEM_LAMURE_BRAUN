package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokedexListFragment
import fr.iutbourg.pokemoncardsexchange.ui.fragment.UserPokedexListFragment
import kotlinx.android.synthetic.main.activity_friend.*
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val userPokedexListFragment = UserPokedexListFragment()
    private val pokedexFragment = PokedexListFragment()
    private lateinit var alertDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        alertDialog = Dialog(this)
        configurePokedexFragment()
        myCard.setOnClickListener{
            fragmentManager.beginTransaction()
                .replace(friendContainer.id, userPokedexListFragment)
                .commit()
        }
        allCard.setOnClickListener{
            fragmentManager.beginTransaction()
                .replace(friendContainer.id, pokedexFragment)
                .commit()
        }
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(friendContainer.id, userPokedexListFragment)
        fragmentTransaction.commit()
    }
}
