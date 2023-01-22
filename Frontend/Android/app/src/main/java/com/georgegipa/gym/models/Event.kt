package com.georgegipa.gym.models

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("startTimeInEpochSeconds")
    val startTime: Int,
    @SerializedName("endTimeInEpochSeconds")
    val endTime: Int,
    val room: String
)