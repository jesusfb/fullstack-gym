package com.georgegipa.gym.models

import android.content.Context
import com.georgegipa.gym.utils.removeUrlFromImage
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val id: Int,
    @SerializedName("user_name")
    val name: String,
    @SerializedName("user_lastname")
    val lastname: String,
    @SerializedName("user_email")
    val email: String,
    @SerializedName("user_address")
    val address: String,
    @SerializedName("image_url")
    private val url: String = "",
    @SerializedName("plan_id")
    val plan: Int
) {
    val image : String
        get() = url.removeUrlFromImage()

    companion object {

        private const val USER_KEY = "user"

        fun retrieveUser(context: Context): User {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val json = prefs.getString(USER_KEY, "")
            return Gson().fromJson(json, User::class.java)
        }

        //save to shared preferences
        fun saveUser(context: Context, user: User) {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString(USER_KEY, Gson().toJson(user))
            editor.apply()
        }

        //check if a user object is in shared preferences
        fun isUserLoggedIn(context: Context): Boolean {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            return prefs.contains(USER_KEY)
        }

        //remove user object from shared preferences
        fun logout(context: Context) {
            val prefs = context.getSharedPreferences(USER_KEY, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.remove(USER_KEY)
            editor.apply()
        }
    }
}