package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.PokedexViewModel
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login_button.setOnClickListener {
            if (login_email_editText.text != null &&  login_password_editText.text != null)
                userViewModel.login(login_email_editText.text.toString(), login_password_editText.text.toString()).observe(this) {
                    it.user.let {user ->
                        user?.token?.let {token ->
                            PreferencesUtils.saveString("current_user_token", token, context!!)
                            val intent = Intent(activity, PokedexActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}