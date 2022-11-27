package com.georgegipa.gym

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val name: String = "Pillates",
    val startTime: String = "Monday 10PM",//TODO: ISO 8601
    val description: String = "Pillates is a combination of pilates and yoga",
    val duration: Int = 50,
    val trainer: String = "Jessica Smith"
) : Parcelable
