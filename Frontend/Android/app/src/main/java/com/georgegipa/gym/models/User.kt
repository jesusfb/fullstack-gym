package com.georgegipa.gym.models

import com.georgegipa.gym.utils.removeUrlFromImage
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val id: Int,
    @SerializedName("user_name")
    val name: String,
    @SerializedName("user_lastname")
    val lastname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("user_address")
    val address: String,
    @SerializedName("image_url")
    private val url: String = "",
    @SerializedName("registered_date")
    val registeredDate: String,
    @SerializedName("plan_id")
    val plan: Int
) {
    val image : String
        get() = url.removeUrlFromImage()
}