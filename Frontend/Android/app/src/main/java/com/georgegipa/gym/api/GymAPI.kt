package com.georgegipa.gym.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface GymAPI {

    @GET("auth/authenticate")
    suspend fun authenticate(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<ResponseBody>

    @GET("courses/all")
    suspend fun getCourses(@Header("Authorization") token: String): Response<ResponseBody>


    @GET("plans/all")
    suspend fun getPlans(@Header("Authorization") token: String): Response<ResponseBody>

    @GET("instructors/all")
    suspend fun getTrainers(@Header("Authorization") token: String): Response<ResponseBody>

    @GET("events")
    suspend fun getEventsForUser(
        @Header("Authorization") token: String,
        @Query("user_id") id: Int
    ): Response<ResponseBody>

    @POST("events/register")
    suspend fun registerCourse(
        @Header("Authorization") token: String,
        @Query("user_id") id: Int,
        @Query("course_id") courseId: Int,
        @Query("start_tmp") startTime: Int,
        @Query("end_tmp") endTime: Int
    ): Response<ResponseBody>

    @DELETE("events/unregister")
    suspend fun unregisterCourse(
        @Header("Authorization") token: String,
        @Query("user_id") id: Int,
        @Query("course_id") courseId: Int,
        @Query("start_tmp") startTime: Int,
        @Query("end_tmp") endTime: Int
    ): Response<ResponseBody>

    @GET("courses/schedule/all/epoch")
    suspend fun getEvents(@Header("Authorization") token: String): Response<ResponseBody>
}