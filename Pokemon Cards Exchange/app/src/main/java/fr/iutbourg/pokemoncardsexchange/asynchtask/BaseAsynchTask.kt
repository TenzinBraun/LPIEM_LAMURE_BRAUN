package fr.iutbourg.pokemoncardsexchange.asynchtask

open class BaseAsynchTask{

    fun makeURLFromUseCase(name: String): String {
        TODO("not implemented")
    }

    open fun callAPIFor(name: String): String {
        val url: String = makeURLFromUseCase(name)
        return ""
    }

}
