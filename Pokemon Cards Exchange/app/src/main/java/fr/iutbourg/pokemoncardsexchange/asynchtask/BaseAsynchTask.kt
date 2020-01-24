package fr.iutbourg.pokemoncardsexchange.asynchtask

import fr.iutbourg.pokemoncardsexchange.service.BaseWebService

open class BaseAsynchTask{

    fun makeURLFromUseCase(name: String): String {
        TODO("not implemented")
    }

    open fun callAPIFor(name: String): String {
        val url: String = makeURLFromUseCase(name)
        BaseWebService.API.ApiURLCard.ALL_CARDS
        return ""
    }

}
