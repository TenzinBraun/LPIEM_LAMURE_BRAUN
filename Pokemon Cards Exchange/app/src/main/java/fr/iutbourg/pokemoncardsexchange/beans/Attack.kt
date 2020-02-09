package fr.iutbourg.pokemoncardsexchange.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Attack: Serializable {
    @SerializedName("cost")
    @Expose
    private var cost: List<String>? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("damage")
    @Expose
    private var damage: String? = null
    @SerializedName("convertedEnergyCost")
    @Expose
    private var convertedEnergyCost: Int? = null

    fun getCost(): List<String>? {
        return cost
    }

    fun setCost(cost: List<String>) {
        this.cost = cost
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getDamage(): String? {
        return damage
    }

    fun setDamage(damage: String) {
        this.damage = damage
    }

    fun getConvertedEnergyCost(): Int? {
        return convertedEnergyCost
    }

    fun setConvertedEnergyCost(convertedEnergyCost: Int?) {
        this.convertedEnergyCost = convertedEnergyCost
    }

}
