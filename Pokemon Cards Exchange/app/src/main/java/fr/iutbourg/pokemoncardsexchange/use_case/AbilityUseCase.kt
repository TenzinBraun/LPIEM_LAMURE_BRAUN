package fr.iutbourg.pokemoncardsexchange.use_case

import fr.iutbourg.pokemoncardsexchange.asynchtask.pokedex.AbilityAsynchTask
import fr.iutbourg.pokemoncardsexchange.utils.PreferenceUtils

class AbilityUseCase: BaseUseCase() {

    val asynchTaskAbility: AbilityAsynchTask? = AbilityAsynchTask()
        .getInstance()

    fun getAbilityName():String? {
        return asynchTaskAbility?.callAPIFor(PreferenceUtils.ABILITY_API)
    }
}