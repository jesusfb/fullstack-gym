package com.georgegipa.gym.models

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("start_timestamp")
    val start: Int,
    @SerializedName("end_timestamp")
    val end: Int,
    val room: String
) {
    fun getReadAbleDate(): String {
        val day  = java.text.SimpleDateFormat("EEE").format(start * 1000L)
        val date = java.text.SimpleDateFormat("dd MMM").format(start * 1000L)
        val start = java.text.SimpleDateFormat("HH:mm").format(start * 1000L)
        val end = java.text.SimpleDateFormat("HH:mm").format(end * 1000L)
        return "$day $date $start - $end"
    }
}