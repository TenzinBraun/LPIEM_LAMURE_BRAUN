package fr.iutbourg.pokemoncardsexchange.asynchtask

interface APILinker {
    fun callAPIFor(name: String): String
    fun makeURLFromUseCase(name: String): String
}
