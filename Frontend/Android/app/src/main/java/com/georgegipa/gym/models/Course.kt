package com.georgegipa.gym.models

import android.os.Parcelable
import com.georgegipa.gym.utils.removeUrlFromImage
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val id: Int,
    @SerializedName("course_name")
    val name: String,
    @SerializedName("course_description")
    val description: String,
    @SerializedName("instructor_id")
    val instructor: Int,
    @SerializedName("plan_ids")
    val plans: List<Int>,
    @SerializedName("image_url")
    private val url: String = ""
) : Parcelable {
    val image: String
        get() = url.removeUrlFromImage()
}
