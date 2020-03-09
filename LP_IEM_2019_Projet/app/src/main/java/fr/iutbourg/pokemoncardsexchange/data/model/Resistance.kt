package fr.iutbourg.pokemoncardsexchange.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Resistance: Serializable {

    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("value")
    @Expose
    private var value: String? = null

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String) {
        this.value = value
    }
}
