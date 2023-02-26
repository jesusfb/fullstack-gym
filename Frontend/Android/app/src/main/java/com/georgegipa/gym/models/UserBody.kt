package com.georgegipa.gym.models

import android.content.Context
import com.google.gson.Gson

data class UserBody(
    val email: String,
    val password: String
) {
    companion object {
        private const val USER_KEY = "user"

        fun retrieveUser(context: Context): UserBody {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val json = prefs.getString(USER_KEY, "")
            return Gson().fromJson(json, UserBody::class.java)
        }

        fun saveUser(context: Context, user: UserBody) {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString(USER_KEY, Gson().toJson(user))
            editor.apply()
        }

        fun isUserLoggedIn(context: Context): Boolean {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            return prefs.contains(USER_KEY)
        }

        fun logout(context: Context) {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.remove(USER_KEY)
            editor.apply()
        }

    }
}