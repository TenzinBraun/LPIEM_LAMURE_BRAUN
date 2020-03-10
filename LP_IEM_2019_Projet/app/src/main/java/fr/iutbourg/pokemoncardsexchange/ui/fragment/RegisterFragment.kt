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
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.activity.PokedexActivity
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.PokedexViewModel
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        register_cancel_button.setOnClickListener {

        }

        register_validate_button.setOnClickListener {
            userViewModel.register(
                register_email_editText.text.toString(),
                register_password_editText.text.toString(),
                register_name_editText.text.toString(),
                register_firstname_editText.text.toString()
            ).observe(this) {
                it.user.let {user ->
                    user?.userID.let {
                        PreferencesUtils.saveInt("current_user_id", user?.userID, context!!)

                    }
                    user?.name?.let {name ->
                        PreferencesUtils.saveString("current_user_name", name, context!!)
                    }
                    user?.firstname?.let {firstname ->
                        PreferencesUtils.saveString("current_user_firstname",firstname, context!!)
                    }
                    val intent = Intent(activity, PokedexActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}