package com.georgegipa.gym.api

import android.util.Log
import com.georgegipa.gym.BuildConfig
import com.georgegipa.gym.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

class GymClient {
    companion object {
        private const val TAG = "GymClient"
        private const val BASE_URL = "${BuildConfig.BASE_URL}api/"
        private var token = ""
            get() {
                if (field.isEmpty()) {
                    throw Exception("Token is empty")
                }
                return "Bearer $field"
            }
        private var userId = 0
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GymAPI::class.java)

    //rewrite this to use suspend functions
    //suspend functions can be called from a coroutine or another suspend function

    suspend fun login(userBody: UserBody): Pair<Int,User?> {
        return try {
            val response = retrofit.authenticate(userBody)
            Log.d(TAG, "LoginURL: ${response.raw().request().url()}")
            //return the status code
            val res = Gson().fromJson<ResponseUser>(
                response.body()?.string(),
                object : TypeToken<ResponseUser>() {}.type
            )
            Log.d(TAG, "Response: ${res.user.name}")
            token = res.token
            userId = res.user.id
            Log.d(TAG, "Response: ${res.token}")
            response.code()
            Pair(response.code(),res.user)
        } catch (e: Exception) {
            //print the trace
            e.printStackTrace()
            Pair(-1,null)
        }
    }

    suspend fun getCourses(): List<Course> {
        return try {
            val response = retrofit.getCourses(token)
            Log.d(TAG, "CourseURL: ${response.raw().request().url()}")
            Gson().fromJson<List<Course>>(
                response.body()?.string(),
                object : TypeToken<List<Course>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPlans(): List<Plan> {
        return try {
            val response = retrofit.getPlans(token)
            Log.d(TAG, "PlanURL: ${response.raw().request().url()}")
            Gson().fromJson(
                response.body()?.string(),
                object : TypeToken<List<Plan>>() {}.type
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getTrainers(): List<Instructor>  {
        return try {
            val response = retrofit.getTrainers(token)
            Log.d(TAG, "TrainerURL: ${response.raw().request().url()}")
            Gson().fromJson(
                response.body()?.string(),
                object : TypeToken<List<Instructor>>() {}.type
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun registerToCourse(courseId : Int, start : Int , end : Int) : Boolean {
        val response = retrofit.registerCourse(token,userId, courseId, start, end)
        Log.d(TAG, "RegisterURL: ${response.raw().request().url()}")
        return response.code() == 200
    }

    suspend fun unregisterFromCourse(courseId : Int, start : Int , end : Int) : Boolean {
        val response = retrofit.unregisterCourse(token,userId, courseId, start, end)
        Log.d(TAG, "UnRegisterURL: ${response.raw().request().url()}")
        return response.code() == 200
    }

    suspend fun getEventsForUser() : List<GymEvent> {
        return try {
            val response = retrofit.getEventsForUser(token,userId)
            Log.d(TAG, "EventURL: ${response.raw().request().url()}")
            Gson().fromJson(
                response.body()?.string(),
                object : TypeToken<List<GymEvent>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getAllEvents(): List<GymEvent> {
        return try {
            val response = retrofit.getEvents(token)
            Log.d(TAG, "EventURL: ${response.raw().request().url()}")
            Gson().fromJson(
                response.body()?.string(),
                object : TypeToken<List<GymEvent>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

}