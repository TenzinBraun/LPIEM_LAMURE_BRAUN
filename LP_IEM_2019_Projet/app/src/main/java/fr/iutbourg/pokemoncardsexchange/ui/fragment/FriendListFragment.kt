package fr.iutbourg.pokemoncardsexchange.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.ui.adapter.FriendAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.FriendViewModel
import fr.iutbourg.pokemoncardsexchange.ui.widget.CustomScrollListener
import kotlinx.android.synthetic.main.pokedex_fragment.view.*

class FriendListFragment: Fragment() {

    private lateinit var friendViewModel: FriendViewModel
    private lateinit var friendAdapter: FriendAdapter
    private lateinit var customScrollListener: CustomScrollListener
    private lateinit var friendList: List<User>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokedex_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // We need to inject the OnCharacterClickListener in the constructor of the adapter
        friendAdapter = FriendAdapter()

        view.recyclerViewImage.layoutManager = GridLayoutManager(activity, 3) as RecyclerView.LayoutManager?
        view.recyclerViewImage.adapter = friendAdapter
        view.recyclerViewImage.addOnScrollListener(customScrollListener)

        friendViewModel.friends.observe(this){
            it.friend?.let { friends ->
                this.friendList = friends
                friendAdapter.submitList(friends)
            }
        }
    }
}
