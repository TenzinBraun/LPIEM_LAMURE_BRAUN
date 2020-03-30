package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    /**
     * Get all cards available on API
     * @return a [Response] of [User]
     */

    @POST(ApiLoginURL.REGISTER)
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("firstname") firstname: String
    ): Response<User>

    @POST(ApiLoginURL.LOGIN)
    @FormUrlEncoded
    suspend fun login (
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<User>

    @POST(ApiLoginURL.LOGIN)
    @FormUrlEncoded
    suspend fun autoLogin (
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<User>


    @POST(ApiLoginURL.AUTO_LOGIN)
    suspend fun autoLogin (
        @Header("token") token: String
    ): Response<User>

    @GET(ApiLoginURL.ME)
    suspend fun infos (
        @Header("token") token: String
    ): Response<User>

    @PUT(ApiLoginURL.ME)
    @FormUrlEncoded
    suspend fun update (
        @Header("token") token: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("firstname") firstname: String
    ): Response<User>

    companion object API{
        const val BASE_URL: String = "http://pokemontcg.ddns.net/api/user/"

        object ApiLoginURL {
            const val REGISTER: String = "register/"
            const val LOGIN: String = "login/"
            const val ME: String = "me/"
            const val AUTO_LOGIN: String = "auto_login/"
        }
    }
}