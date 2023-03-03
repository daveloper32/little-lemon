package com.daveloper.littlelemon.data.manager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.daveloper.littlelemon.utils.isValidEmail

class LittleLemonPreferencesManager(
    context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "little_lemon_preferences"
        private const val KEY_FIRST_NAME ="key_first_name"
        private const val KEY_LAST_NAME ="key_last_name"
        private const val KEY_EMAIL ="key_email"
    }
    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
    private val preferencesEditor = sharedPreferences.edit()

    var firstName
        get() = sharedPreferences.getString(KEY_FIRST_NAME, "") ?: ""
        set(value) = putString(KEY_FIRST_NAME, value)

    var lastName
        get() = sharedPreferences.getString(KEY_LAST_NAME, "") ?: ""
        set(value) = putString(KEY_LAST_NAME, value)

    var email
        get() = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
        set(value) = putString(KEY_EMAIL, value)

    fun userIsLoggedIn(): Boolean {
        return firstName.isNotEmpty() &&
                lastName.isNotEmpty() &&
                email.isNotEmpty() &&
                email.isValidEmail()
    }

    private fun putString(key: String, value: String) {
        with(preferencesEditor) {
            putString(key, value)
            commit()
        }
    }
}