package fr.iutbourg.pokemoncardsexchange.asynchtask.pokedex

import android.util.Log
import android.view.View
import fr.iutbourg.pokemoncardsexchange.beans.Card
import fr.iutbourg.pokemoncardsexchange.beans.Pokedex
import fr.iutbourg.pokemoncardsexchange.presenter.PokedexPresenter
import fr.iutbourg.pokemoncardsexchange.service.BaseWebService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AllCardAPI {

    @GET(BaseWebService.API.ApiURLCard.ALL_CARDS)
    fun getAllCard(): Call<Pokedex>
}


class PokedexAsyncTask {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseWebService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: AllCardAPI = retrofit.create(AllCardAPI::class.java)
    private val pokedex = service.getAllCard()


    fun getAllCardFromAPI(block: (List<Card>) -> Unit) {

        Log.d("TAGGG", pokedex.request().toString())

        pokedex.enqueue(object: Callback<Pokedex> {

            override fun onFailure(call: Call<Pokedex>, t: Throwable) {
                Log.d("TAGGG", "erreurrr")
                Log.d("TAGGG", t.message)
            }

            override fun onResponse(call: Call<Pokedex>, response: Response<Pokedex>) {
                response.body()?.getCards()?.filter {
                    it.supertype.equals("Pokémon")
                }?.run {
                    block(this)
                }
            }
        })
    }
}