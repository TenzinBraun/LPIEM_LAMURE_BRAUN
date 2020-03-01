package fr.iutbourg.pokemoncardsexchange.data.manager

import fr.iutbourg.pokemoncardsexchange.data.networking.api.PokedexApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


/**
 * Implementation of [HttpClientManager]
 */
private object HttpClientManagerImpl : HttpClientManager {

    override val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(PokedexApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

/**
 * Manager use to access to Retrofit resource
 */
interface HttpClientManager {

    /**
     * Instance of Retrofit used to create Api
     */
    val retrofit: Retrofit

    companion object Instance {
        /**
         * Singleton for the interface
         */
        val instance: HttpClientManager = HttpClientManagerImpl

    }

}

inline fun <reified T> HttpClientManager.createApi(): T {
    return this.retrofit.create()
}