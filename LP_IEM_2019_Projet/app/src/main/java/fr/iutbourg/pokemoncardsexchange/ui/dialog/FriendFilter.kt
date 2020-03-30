package fr.iutbourg.pokemoncardsexchange.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.ui.adapter.FriendAdapter
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.FriendViewModel
import fr.iutbourg.pokemoncardsexchange.ui.viewmodel.PokedexViewModel

class FriendFilter(
    private var filterUserList: List<User>,
    context: Context,
    private var adapter: FriendAdapter,
    private var activity: FragmentActivity
) :
    BaseDialog(context, activity) {

    private var queryName: String = String()
    private var inputUserNameEditText: EditText = EditText(context)
    private lateinit var inputUserName: String
    private lateinit var friendViewModel: FriendViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.create_friend_filter_modal)
        setSizeForDialog()
        activity.run {
            friendViewModel = ViewModelProvider(activity, PokedexViewModel).get()
        }
//        inputUserNameEditText.addTextChangedListener { editable ->
//            if (editable?.length!! > 3) {
//                friendViewModel.friends.observe(activity) { friendResponse ->
//                    friendResponse.friend?.let { users ->
//                        filterUserList = users
//                        adapter.submitList(filterUserList)
//                    }
//                }
//            }
//        }
    }

    private fun appendName(param: String): FriendFilter {
        queryName = param
        return this
    }

    private fun filter(): FriendFilter {
        return this
    }


}