package com.georgegipa.gym.api

import com.georgegipa.gym.TEMP_USER_ID
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GymAPI {
    @GET("courses/all")
    suspend fun getCourses(): Response<ResponseBody>

    @GET("plans/all")
    suspend fun getPlans(): Response<ResponseBody>

    @GET("instructors/all")
    suspend fun getTrainers(): Response<ResponseBody>

    @GET("users")
    suspend fun getUser(
        @Query("user_id") id: Int
    ): Response<ResponseBody>

    @GET("events")
    suspend fun getEventsForUser(
        @Query("user_id") id: Int
    ): Response<ResponseBody>

    @POST("events")
    suspend fun registerCourse(
        @Query("user_id") id: Int,
        @Query("course_id") courseId: Int,
        @Query("startTimeInEpochSeconds") startTime: Int,
        @Query("endTimeInEpochSeconds") endTime: Int
    ): Response<ResponseBody>

    @DELETE("events")
    suspend fun unregisterCourse(
        @Query("user_id") id: Int,
        @Query("course_id") courseId: Int,
        @Query("startTimeInEpochSeconds") startTime: Int,
        @Query("endTimeInEpochSeconds") endTime: Int
    ): Response<ResponseBody>

    @GET("courses/events/allEpoch")
    suspend fun getEvents(): Response<ResponseBody>
}