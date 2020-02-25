package fr.iutbourg.pokemoncardsexchange.ui.widget

import androidx.recyclerview.widget.RecyclerView

class CustomScrollListener(private val callBackScroll: CallBackScroll) :
    RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        when {
            dy > 0 -> callBackScroll.notiftyScroll(1)
            dy < 0 -> callBackScroll.notiftyScroll(2)
            else -> callBackScroll.notiftyScroll(0)
        }
    }
}
