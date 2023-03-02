package com.georgegipa.gym.utils

import android.content.Context
import android.net.ConnectivityManager
import com.georgegipa.gym.BuildConfig
import java.time.LocalDateTime

//the url of the images is http://localhost:8080/... so we need to remove it
// and replace it with the actual url if the app is deployed
fun String.removeUrlFromImage() : String {
    return if(this.contains("http://localhost:8080/"))
     this.replace("http://localhost:8080/", BuildConfig.BASE_URL)
    else this
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

//check if the device is connected to the internet
@Suppress("DEPRECATION")
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}