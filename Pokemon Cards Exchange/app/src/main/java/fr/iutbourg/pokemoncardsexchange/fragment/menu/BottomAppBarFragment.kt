package fr.iutbourg.pokemoncardsexchange.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import fr.iutbourg.pokemoncardsexchange.R
import kotlinx.android.synthetic.main.bottom_appbar.*

class BottomAppBarFragment: Fragment(), AppBottomView {

    private lateinit var bottomView: View
    private lateinit var context: BottomAppBarFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomView = inflater.inflate(R.layout.bottom_appbar, container, false)
        return bottomView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.toString()
    }


    override fun update(responseCode: Int) {
        submitResponseCode(responseCode)
    }

    private fun submitResponseCode(responseCode: Int) {
        when {
            responseCode == 1 -> myBottomAppBar.performHide()
            responseCode == 2 -> myBottomAppBar.performShow()
        }
    }

    companion object {
        const val KEY_BOTTOM_BAR = "myBottomBar"
        fun newInstance(myKey: String) = BottomAppBarFragment().apply {
            arguments = bundleOf(
                KEY_BOTTOM_BAR to myKey)
        }
    }
}

interface AppBottomView {
    fun update(responseCode :Int)
}