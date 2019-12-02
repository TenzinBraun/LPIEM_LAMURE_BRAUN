package fr.iutbourg.pokemoncardsexchange.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Ability: Serializable {
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null

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

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }
}