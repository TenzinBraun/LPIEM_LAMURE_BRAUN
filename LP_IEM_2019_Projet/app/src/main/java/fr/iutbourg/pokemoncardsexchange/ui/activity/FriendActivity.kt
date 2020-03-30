package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.ui.fragment.HistoryExchangeCardFragment
import fr.iutbourg.pokemoncardsexchange.ui.fragment.FriendListFragment
import fr.iutbourg.pokemoncardsexchange.ui.fragment.MyFriendsFragment
import fr.iutbourg.pokemoncardsexchange.ui.fragment.NewFriendsAddedFragment
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val friendListFragment = MyFriendsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        configureFriendListFragment()
        configureAllNavigation()
    }

    private fun configureAllNavigation() {
        supportFragmentManager.beginTransaction()
        allMyFriends.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(friendContainer.id, MyFriendsFragment())
                .commit()
        }
        searchPeople.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(friendContainer.id, FriendListFragment())
                .commit()
        }
        historyExchangeCard.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(friendContainer.id,
                    HistoryExchangeCardFragment()
                )
                .commit()
        }
        newFriendsAdded.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(friendContainer.id, NewFriendsAddedFragment())
                .commit()
        }
    }

    private fun configureFriendListFragment() {
        fragmentTransaction.add(friendContainer.id, friendListFragment)
        fragmentTransaction.commit()
    }
}
