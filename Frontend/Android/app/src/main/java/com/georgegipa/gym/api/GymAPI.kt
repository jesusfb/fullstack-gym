package com.georgegipa.gym.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GymAPI {

    @GET("courses/all")
    suspend fun getCourses(): Response<ResponseBody>

    @GET("plans/id/{course_id}/courses/all")
    suspend fun getCoursesByPlanId(@Path(value = "course_id") id : Int): Response<ResponseBody>

    @GET("plans/all")
    suspend fun getPlans(): Response<ResponseBody>

    @GET("trainers/all")
    suspend fun getTrainers(): Response<ResponseBody>

    @GET("users/id/{user_id}")
    suspend fun getUserById(@Path(value = "user_id") id : Int): Response<ResponseBody>
}