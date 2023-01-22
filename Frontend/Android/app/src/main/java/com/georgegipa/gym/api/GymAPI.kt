package com.georgegipa.gym.api

import com.georgegipa.gym.TEMP_USER_ID
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
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

    @GET("courses/events/allEpoch")
    suspend fun getEvents(): Response<ResponseBody>
}