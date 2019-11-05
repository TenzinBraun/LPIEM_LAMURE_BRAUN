package fr.iutbourg.pokemoncardsexchange.asynchtask

class AsynchTaskAbility : BaseAsynchTask() {

    private var asynchTaskAbility: AsynchTaskAbility? = null

    fun getInstance(): AsynchTaskAbility? {
        if (asynchTaskAbility == null) {
            asynchTaskAbility = AsynchTaskAbility()
            return asynchTaskAbility
        }
        return asynchTaskAbility
    }

    override fun callAPIFor(name: String): String {
        return super.callAPIFor(name)
    }
}