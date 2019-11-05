package fr.iutbourg.pokemoncardsexchange.use_case

import fr.iutbourg.pokemoncardsexchange.asynchtask.AsynchTaskAbility
import fr.iutbourg.pokemoncardsexchange.utils.PreferenceUtils

class AbilityUseCase: BaseUseCase() {

    val asynchTaskAbility: AsynchTaskAbility? = AsynchTaskAbility().getInstance()

    fun getAbilityName():String? {
        return asynchTaskAbility?.callAPIFor(PreferenceUtils.ABILITY_API)
    }
}