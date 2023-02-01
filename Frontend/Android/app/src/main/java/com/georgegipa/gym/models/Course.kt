package com.georgegipa.gym.models

import android.os.Parcelable
import com.georgegipa.gym.api.ApiResponses
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
    val instructorId: Int,
    @SerializedName("plan_ids")
    val plans: List<Int>,
    @SerializedName("image_url")
    private val url: String = ""
) : Parcelable {
    val image: String
        get() = url.removeUrlFromImage()

    val schedule : String
        get()  {
            ApiResponses.events.find { it.courseId == id }?.let { event ->
                //given 2 timestamps, return Mon 19:00 - 20:00
                val start = java.text.SimpleDateFormat("HH:mm").format(event.startTime * 1000L)
                val end = java.text.SimpleDateFormat("HH:mm").format(event.endTime * 1000L)
                val day = java.text.SimpleDateFormat("EEE").format(event.startTime * 1000L)
                return "$day $start - $end"
            }
            return ""
        }

    val room : String
        get() {
            ApiResponses.events.find { it.courseId == id }?.let { event ->
                return event.room
            }
            return ""
        }
}
