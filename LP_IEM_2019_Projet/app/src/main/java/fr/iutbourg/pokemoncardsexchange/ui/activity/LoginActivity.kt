package fr.iutbourg.pokemoncardsexchange.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.ui.fragment.LoginFragment
import fr.iutbourg.pokemoncardsexchange.ui.fragment.RegisterFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private val loginFragment = LoginFragment()
    private val registerFragment = RegisterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        configureLoginFragment()

        not_register_yet_btn.setOnClickListener {
            fragmentManager
                .beginTransaction()
                .replace(loginContainer.id, registerFragment)
                .commit()
        }
    }

    private fun configureLoginFragment() {
        fragmentTransaction.add(loginContainer.id, loginFragment)
        fragmentTransaction.commit()
    }

}