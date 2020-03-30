package fr.iutbourg.pokemoncardsexchange.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.R
import fr.iutbourg.pokemoncardsexchange.data.model.User
import kotlinx.android.synthetic.main.myfriend_view_holder.view.*

class MyFriendHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bindFriend(user: User) {
        itemView.myFriend.text = user.name
    }

    companion object {
        fun create(parent: ViewGroup) : MyFriendHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.myfriend_view_holder, parent, false)
            return MyFriendHolder(view)
        }
    }

}
