package fr.iutbourg.pokemoncardsexchange.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iutbourg.pokemoncardsexchange.data.model.User
import fr.iutbourg.pokemoncardsexchange.ui.fragment.ExchangeCallback
import fr.iutbourg.pokemoncardsexchange.ui.widget.MyFriendHolder
import kotlinx.android.synthetic.main.myfriend_view_holder.view.*

class MyFriendAdapter(private var callback: ExchangeCallback): RecyclerView.Adapter<MyFriendHolder>() {

    private var myFriends = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFriendHolder =
        MyFriendHolder.create(parent)


    override fun getItemCount(): Int = myFriends.size


    override fun onBindViewHolder(holder: MyFriendHolder, position: Int) {
        holder.bindFriend(myFriends[position])
        holder.itemView.exchangeCard.setOnClickListener {
            callback.exchangeCardWith(userID = myFriends[position].userID)
        }
    }

    fun submitList(users: List<User>) {
        myFriends = users
        notifyDataSetChanged()
    }

}
