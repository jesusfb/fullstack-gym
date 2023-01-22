package com.georgegipa.gym.utils

import android.content.Context
//TODO: REMOVE PASSWORD STORING FROM SHARED PREFERENCES

fun Context.saveUserCredentials(email: String, code: String) {
    val editor = this.getSharedPreferences("user_credentials", Context.MODE_PRIVATE).edit()
    editor.putString("email", email)
    editor.putString("code", code)
    editor.apply()
}

fun Context.getUserEmail() = this.getSharedPreferences("user_credentials", Context.MODE_PRIVATE).getString("email", "")

fun Context.getUserCode() = this.getSharedPreferences("user_credentials", Context.MODE_PRIVATE).getString("code", "")

fun Context.clearUserCredentials() {
    val editor = this.getSharedPreferences("user_credentials", Context.MODE_PRIVATE).edit()
    editor.clear()
    editor.apply()
}

fun Context.isUserLoggedIn() = this.getSharedPreferences("user_credentials", Context.MODE_PRIVATE).contains("email")