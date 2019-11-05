package fr.iutbourg.pokemoncardsexchange.use_case

import fr.iutbourg.pokemoncardsexchange.asynchtask.AsynchTaskAbility

class AbilityUseCase: BaseUseCase() {

    val asynchTaskAbility: AsynchTaskAbility.getInstance();

    fun getAbilityName():String {
        return asynchTaskAbility.callAPIFor("ability_name")
    }
}