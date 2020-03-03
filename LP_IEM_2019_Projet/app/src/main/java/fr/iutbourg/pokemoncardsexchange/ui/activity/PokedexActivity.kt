package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.ui.fragment.PokedexListFragment
import fr.iutbourg.pokemoncardsexchange.ui.widget.CallBackScroll
import kotlinx.android.synthetic.main.activity_pokedex.*

class PokedexActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val pokedexFragment = PokedexListFragment()
    private lateinit var alertDialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        alertDialog = Dialog(this)
        configurePokedexFragment()
    }

    private fun configurePokedexFragment() {
        fragmentTransaction.add(pokedexContainer.id, pokedexFragment)
        fragmentTransaction.commit()
    }
}
