package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.data.utils.PreferencesUtils
import fr.iutbourg.pokemoncardsexchange.ui.adapter.MyFriendAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.FriendViewModel
import kotlinx.android.synthetic.main.activity_friend.*
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class MyFriendsFragment : Fragment(), ExchangeCallback {


    private lateinit var friendViewModel: FriendViewModel
    private lateinit var friendAdapter: MyFriendAdapter
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

        friendAdapter = MyFriendAdapter(this)

        view.recyclerViewImage.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewImage.adapter = friendAdapter

        friendViewModel.getFriends(
                PreferencesUtils.getString(
                    "current_user_token",
                    "",
                    requireContext()
                )!!
            )
            .observe(this) {
                it.myFriends?.usersFriend?.let { user ->
                    this.friendList = user
                    friendAdapter.submitList(user)
                }
            }

    }

    override fun exchangeCardWith(userID: Int?) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(requireActivity().friendContainer.id, ExchangeCardFragment(userID))
            .addToBackStack(this.javaClass.name)
            .commit()
    }
}

interface ExchangeCallback {
    fun exchangeCardWith(userID: Int?)

}
