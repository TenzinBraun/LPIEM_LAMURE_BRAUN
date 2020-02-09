package fr.iutbourg.pokemoncardsexchange.asynchtask

import fr.iutbourg.pokemoncardsexchange.service.BaseWebService

open class BaseAsynchTask{

    private fun makeURLFromUseCase(): String {
        TODO("not implemented")
    }

    open fun callAPIFor(name: String): String {
        makeURLFromUseCase()
        BaseWebService.API.ApiURLCard.ALL_CARDS
        return ""
    }

}
