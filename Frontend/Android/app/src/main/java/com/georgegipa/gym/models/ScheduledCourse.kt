package com.georgegipa.gym.models

import com.google.gson.annotations.SerializedName

data class ScheduledCourse(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("end_timestamp")
    val end: Int,
    @SerializedName("start_timestamp")
    val start: Int
) {
    
}