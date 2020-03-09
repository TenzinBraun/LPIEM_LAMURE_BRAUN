package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Card: Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("nationalPokedexNumber")
    @Expose
    var nationalPokedexNumber: Int? = null
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null
    @SerializedName("imageUrlHiRes")
    @Expose
    var imageUrlHiRes: String? = null
    @SerializedName("types")
    @Expose
    var types: List<String>? = null
    @SerializedName("supertype")
    @Expose
    var supertype: String? = null
    @SerializedName("subtype")
    @Expose
    var subtype: String? = null
    @SerializedName("hp")
    @Expose
    var hp: String? = null
    @SerializedName("retreatCost")
    @Expose
    var retreatCost: List<String>? = null
    @SerializedName("convertedRetreatCost")
    @Expose
    var convertedRetreatCost: Int? = null
    @SerializedName("number")
    @Expose
    var number: String? = null
    @SerializedName("artist")
    @Expose
    var artist: String? = null
    @SerializedName("rarity")
    @Expose
    var rarity: String? = null
    @SerializedName("series")
    @Expose
    var series: String? = null
    @SerializedName("set")
    @Expose
    var set: String? = null
    @SerializedName("setCode")
    @Expose
    var setCode: String? = null
    @SerializedName("attacks")
    @Expose
    var attacks: List<Attack>? = null
    @SerializedName("resistances")
    @Expose
    var resistances: List<Resistance>? = null
    @SerializedName("weaknesses")
    @Expose
    var weaknesses: List<Weakness>? = null
    @SerializedName("text")
    @Expose
    var text: List<String>? = null
    @SerializedName("evolvesFrom")
    @Expose
    var evolvesFrom: String? = null
    @SerializedName("ability")
    @Expose
    var ability: Ability? = null

}
