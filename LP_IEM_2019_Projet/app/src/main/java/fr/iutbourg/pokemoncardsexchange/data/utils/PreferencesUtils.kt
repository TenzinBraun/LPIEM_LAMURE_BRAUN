package fr.iutbourg.pokemoncardsexchange.data.utils

import android.content.Context


class PreferencesUtils {
    companion object {
        private const val COMMON_PREFERENCE = "common_preference"

        fun saveString(
            key: String,
            value: String,
            context: Context
        ) {
            val commonPreference = context.getSharedPreferences(COMMON_PREFERENCE,
                Context.MODE_PRIVATE
            )
            val configEditor = commonPreference.edit()
            configEditor.putString(key, value)
            configEditor.apply()
        }

        fun getString(
            key: String,
            defaultVal: String,
            context: Context
        ): String? {
            val commonPreference = context.getSharedPreferences(COMMON_PREFERENCE,
                Context.MODE_PRIVATE
            )
            return commonPreference.getString(key, defaultVal)
        }

        fun remove(key: String, context: Context) {
            val commonPreference = context.getSharedPreferences(COMMON_PREFERENCE,
                Context.MODE_PRIVATE
            )
            val configEditor = commonPreference.edit()
            configEditor.remove(key)
            configEditor.apply()
        }

        fun saveInt(key: String, id: Int?, context: Context) {
            val commonPreference = context.getSharedPreferences(COMMON_PREFERENCE,
                Context.MODE_PRIVATE
            )
            val configEditor = commonPreference.edit()
            configEditor.putInt(key, id!!)
            configEditor.apply()
        }
    }
}