package com.georgegipa.gym.models

import com.georgegipa.gym.utils.removeUrlFromImage
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
    private val url: String = "",
    @SerializedName("instructor_specialty")
    val specialty: String
) {
    val image : String
        get() = url.removeUrlFromImage()
}
