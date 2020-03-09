package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokedexListFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val pokedexFragment = PokedexListFragment(PreferencesUtils.getInt("user", -1, this))
    private lateinit var alertDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        alertDialog = Dialog(this)
        configurePokedexFragment()
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(friendContainer.id, pokedexFragment)
        fragmentTransaction.commit()
    }
}
