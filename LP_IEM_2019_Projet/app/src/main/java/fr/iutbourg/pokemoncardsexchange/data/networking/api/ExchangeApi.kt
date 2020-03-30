package fr.iutbourg.pokemoncardsexchange.data.networking.api

import fr.iutbourg.pokemoncardsexchange.data.model.Propositions
import retrofit2.Response
import retrofit2.http.*

interface ExchangeApi {


    @POST(ApiExchangeCard.EXCHANGE)
    suspend fun exchange(
        @Header("token") token: String,
        @Field("User2Id") userID: Int?,
        @Field("cardUserWant") targetCardID: String?,
        @Field("cardUserGive") myCardID: String?
    )
    @POST(ApiExchangeCard.EXCHANGE_VALIDATE)
    suspend fun validateExchange(
        @Header("token") token: String,
        @Field("User2Id") userID: Int?,
        @Field("cardUserWant") targetCardID: String?,
        @Field("cardUserGive") myCardID: String?
    )
    @POST(ApiExchangeCard.EXCHANGE_CANCEL)
    suspend fun cancelExchange(
        @Header("token") token: String,
        @Field("User2Id") userID: Int?,
        @Field("cardUserWant") targetCardID: String?,
        @Field("cardUserGive") myCardID: String?
    )
    @POST(ApiExchangeCard.EXCHANGE_DECLINE)
    suspend fun declineExchange(
        @Header("token") token: String,
        @Field("User2Id") userID: Int?,
        @Field("cardUserWant") targetCardID: String?,
        @Field("cardUserGive") myCardID: String?
    )

    @GET(ApiExchangeCard.MY_PROPOSITIONS)
    suspend fun getMyPropositions(
        @Header("token") token: String,
        @Path("userID") userID: Int?
    )
    @GET(ApiExchangeCard.PROPOSITIONS)
    fun getPropositionsMade(@Header("token") token: String): Response<List<Propositions>>

    companion object ExchangeAPI {

        const val BASE_URL: String = "http://pokemontcg.ddns.net/api/user"

        object ApiExchangeCard {
            const val EXCHANGE: String = "exchange"
            const val EXCHANGE_VALIDATE: String = "exchange/validate"
            const val EXCHANGE_CANCEL: String = "exchange/cancel"
            const val EXCHANGE_DECLINE: String = "exchange/decline"
            const val MY_PROPOSITIONS: String = "{userID}/exchanges"
            const val PROPOSITIONS : String = "exchanges"
        }
    }

}
