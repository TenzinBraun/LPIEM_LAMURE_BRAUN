package fr.iutbourg.pokemoncardsexchange.presenter

import fr.iutbourg.pokemoncardsexchange.fragment.menu.BottomAppBarFragment

class BottomAppBarPresenter(private val bottomAppBarFragment: BottomAppBarFragment) {

    fun notifyMovingScroll(responsCode: Int) {
            bottomAppBarFragment.update(responsCode)
        }
}