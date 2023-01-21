package com.georgegipa.gym.models

import com.georgegipa.gym.utils.removeUrlFromImage
import com.google.gson.annotations.SerializedName

data class Plan(
    @SerializedName("id")
    val id: Int,
    @SerializedName("plan_type")
    val name: String,
    @SerializedName("plan_desc")
    val description: String,
    @SerializedName("plan_price")
    val price: Float,
    @SerializedName("plan_duration")
    val duration: Int,
    @SerializedName("image_url")
    private val url: String = ""
) {
    val image : String
        get() = url.removeUrlFromImage()
}
