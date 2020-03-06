package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.ui.fragment.FriendListFragment
import kotlinx.android.synthetic.main.activity_pokedex.*

class FriendActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val friendListFragment = FriendListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
        configureFriendListFragment()
    }

    private fun configureFriendListFragment() {
        fragmentTransaction.add(friendContainer.id, friendListFragment)
        fragmentTransaction.commit()
    }
}
