package com.georgegipa.gym.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GymAPI {

    @GET("courses/all")
    fun getCourses(): Call<ResponseBody>

    @GET("plans/id/{course_id}/courses/all")
    fun getCoursesByPlanId(@Path(value = "course_id") id : Int): Call<ResponseBody>

    @GET("plans/all")
    fun getPlans(): Call<ResponseBody>

    @GET("trainers/all")
    fun getTrainers(): Call<ResponseBody>

    @GET("users/id/{user_id}")
    fun getUserById(@Path(value = "user_id") id : Int): Call<ResponseBody>
}