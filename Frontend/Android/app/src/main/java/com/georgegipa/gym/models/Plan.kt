package com.georgegipa.gym.models

import com.georgegipa.gym.PLACEHOLDER_PLAN_IMG
import com.google.gson.annotations.SerializedName

data class Plan(
    val id: Int,
    @SerializedName("plan_type")
    val type: String,
    @SerializedName("plan_desc")
    val description: String,
    @SerializedName("plan_price")
    val price: Float,
    @SerializedName("plan_duration")
    val duration: Int,
    @SerializedName("plan_courses")
    val courses: List<Int> = emptyList(),
    @SerializedName("image_url")
    private val url: String = ""
) {
    val image : String
        get() = url.ifEmpty { PLACEHOLDER_PLAN_IMG }
}
