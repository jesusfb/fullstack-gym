package com.georgegipa.gym.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    @SerializedName("course_name")
    val name: String = "Pillates",
    @SerializedName("course_date")
    val date: String = "Monday 10PM",//TODO: ISO 8601
    @SerializedName("course_description")
    val description: String = "Pillates is a combination of pilates and yoga",
    //val duration: Int = 50,
    val trainer: String = "Unknown Trainer"
) : Parcelable
