package com.georgegipa.gym.utils

import android.content.Context
import android.net.ConnectivityManager
import com.georgegipa.gym.api.GymClient.Companion.URL
import java.time.LocalDateTime

fun String.removeUrlFromImage() : String {
    return this.replace("http://localhost:8080/", URL)
}

//create a greeting based on the current time
fun getGreeting(): String {
    val current = LocalDateTime.now()
    val hour = current.hour
    return when {
        hour < 12 -> "Good morning"
        hour < 18 -> "Good afternoon"
        else -> "Good evening"
    }
}

@Suppress("DEPRECATION")
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}