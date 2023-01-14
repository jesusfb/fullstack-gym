package com.georgegipa.gym.models

import com.georgegipa.gym.PLACEHOLDER_INSTRUCTOR_IMG
import com.google.gson.annotations.SerializedName

data class Instructor(
    val id: Int,
    @SerializedName("instructor_name")
    val name: String,
    @SerializedName("instructor_lastname")
    val surname: String,
    @SerializedName("instructor_email")
    val email: String,
    @SerializedName("image_url")
    private val url: String = ""
) {
    val image : String
        get() = url.ifEmpty { PLACEHOLDER_INSTRUCTOR_IMG }
}
