package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.adapter.FriendAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.FriendViewModel
import fr.iutbourg.pokemoncardsexchange.ui.widget.AddFriendCallbackHandler
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class FriendListFragment : Fragment(), AddFriendCallbackHandler {

    private lateinit var friendViewModel: FriendViewModel
    private lateinit var friendAdapter: FriendAdapter
    private lateinit var friendList: List<User>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            friendViewModel = ViewModelProvider(this, FriendViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.pokedex_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        customScrollListener = CustomScrollListener(this)

        friendAdapter = FriendAdapter(this)

        view.recyclerViewImage.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewImage.adapter = friendAdapter
//        view.recyclerViewImage.addOnScrollListener(customScrollListener)

        friendViewModel.getPeople(
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!
            )
            .observe(this) {
                it.pokeFriends?.myUsers?.let { user ->
                    this.friendList = user
                    friendAdapter.submitList(user)
                }
            }
    }

    override fun addFriendsOnClick(userID: Int) {

        friendViewModel.addFriends(userID,
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!)
            .observe(this) {
                Toast.makeText(
                    requireContext(), it.relationShip?.flag, Toast.LENGTH_SHORT).show()
            }
    }
}
