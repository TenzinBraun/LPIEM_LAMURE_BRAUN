package fr.iutbourg.pokemoncardsexchange.data.manager

import fr.iutbourg.pokemoncardsexchange.data.networking.api.ExchangeApi
import fr.iutbourg.pokemoncardsexchange.data.networking.api.FriendApi
import fr.iutbourg.pokemoncardsexchange.data.networking.api.PokedexApi
import fr.iutbourg.pokemoncardsexchange.data.networking.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


/**
 * Implementation of [HttpClientManager]
 */
private object HttpClientManagerImpl : HttpClientManager {


    val interptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interptor)
        .build()

    override val pokedexRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(PokedexApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override val friendRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(FriendApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    override val userRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(UserApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    override val exchangeInstance: Retrofit =
        Retrofit.Builder()
            .baseUrl(ExchangeApi.BASE_URL)
            .client(client)
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
    val pokedexRetrofit: Retrofit
    val friendRetrofit: Retrofit
    val userRetrofit: Retrofit
    val exchangeInstance: Retrofit

    companion object Instance {
        /**
         * Singleton for the interface
         */
        val pokedexInstance: HttpClientManager = HttpClientManagerImpl
        val friendInstance: HttpClientManager = HttpClientManagerImpl
        val userInstance: HttpClientManager = HttpClientManagerImpl
        val exchangeInstance: HttpClientManager = HttpClientManagerImpl

    }

}

inline fun <reified T> HttpClientManager.createPokedexApi(): T {
    return this.pokedexRetrofit.create()
}

inline fun <reified T> HttpClientManager.createFriendApi(): T {
    return this.friendRetrofit.create()
}

inline fun <reified T> HttpClientManager.createUserApi(): T {
    return this.userRetrofit.create()
}

inline fun <reified T> HttpClientManager.createExchangeApi(): T {
    return this.exchangeInstance.create()
}
