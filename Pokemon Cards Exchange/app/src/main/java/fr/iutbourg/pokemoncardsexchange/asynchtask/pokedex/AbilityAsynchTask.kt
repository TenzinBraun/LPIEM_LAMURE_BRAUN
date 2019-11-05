package fr.iutbourg.pokemoncardsexchange.asynchtask.pokedex

import fr.iutbourg.pokemoncardsexchange.asynchtask.BaseAsynchTask

class AbilityAsynchTask : BaseAsynchTask() {

    private var asynchTaskAbility: AbilityAsynchTask? = null

    fun getInstance(): AbilityAsynchTask? {
        if (asynchTaskAbility == null) {
            asynchTaskAbility =
                AbilityAsynchTask()
            return asynchTaskAbility
        }
        return asynchTaskAbility
    }

    override fun callAPIFor(name: String): String {
        return super.callAPIFor(name)
    }
}