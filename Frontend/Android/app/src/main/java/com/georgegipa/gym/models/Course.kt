package com.georgegipa.gym.models

import android.os.Parcelable
import com.georgegipa.gym.PLACEHOLDER_COURSE_IMG
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    @SerializedName("course_name")
    val name: String = "Pillates",
    @SerializedName("course_date")
    @Deprecated("Use start and end time instead")
    val date: String = "Monday 10PM",
    @SerializedName("course_description")
    val description: String = "Pillates is a combination of pilates and yoga",
    val start_time: Int,
    val end_time: Int,
    @SerializedName("instructor_id")
    val instructor: String = "Unknown Trainer",
    @SerializedName("plan_ids")
    val plans: List<Int> = emptyList(),
    @SerializedName("image_url")
    private val url: String = ""
) : Parcelable {
    val image: String
        get() = url.ifEmpty { PLACEHOLDER_COURSE_IMG }
}
