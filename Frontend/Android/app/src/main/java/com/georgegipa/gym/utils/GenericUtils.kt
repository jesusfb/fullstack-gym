package com.georgegipa.gym.utils

import com.georgegipa.gym.api.GymClient.Companion.URL

fun String.removeUrlFromImage() : String {
    return this.replace("http://localhost:8080/", URL)
}