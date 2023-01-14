package com.georgegipa.gym.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface GymAPI {
    @GET("courses/all")
    suspend fun getCourses(): Response<ResponseBody>

    @GET("plans/all")
    suspend fun getPlans(): Response<ResponseBody>

    @GET("instructors/all")
    suspend fun getTrainers(): Response<ResponseBody>
}