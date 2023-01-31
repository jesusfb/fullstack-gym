package com.georgegipa.gym.utils

import kotlinx.datetime.*

private val timezone = TimeZone.currentSystemDefault()

//calculate and return the end date of a plan
fun calculateEndDate(startDate: String, duration: Int) : String {
    val start = startDate+ "T00:00:00Z"
    val instantInThePast: Instant = Instant.parse(start)
    val endDate = instantInThePast.plus(DateTimePeriod(days = duration), timezone)
    return (endDate.toLocalDateTime(timezone).date).toString()
}