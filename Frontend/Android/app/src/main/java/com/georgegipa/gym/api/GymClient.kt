package com.georgegipa.gym.api

import android.util.Log
import com.georgegipa.gym.models.Course
import com.georgegipa.gym.models.Instructor
import com.georgegipa.gym.models.Plan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymClient {
    companion object {
        private const val TAG = "GymClient"
        private const val URL = "http://192.168.1.9:8080"
        private const val BASE_URL = "$URL/api/"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GymAPI::class.java)

    //rewrite this to use suspend functions
    //suspend functions can be called from a coroutine or another suspend function

    suspend fun checkAPI() : Boolean {
        //hit the base url to check if the server is up and running
        //if response is 200, then the server is up and running
        return try {
            val response = retrofit.getCourses()
            response.code() == 200
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getCourses() : List<Course> {
        return try {
            val response = retrofit.getCourses()
            Log.d(TAG, "CourseURL: ${response.raw().request().url()}")
            Gson().fromJson<List<Course>>(
                response.body()?.string(),
                object : TypeToken<List<Course>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPlans() : List<Plan> {
        return try {
            val response = retrofit.getPlans()
            Log.d(TAG, "PlanURL: ${response.raw().request().url()}")
            Gson().fromJson<List<Plan>>(
                response.body()?.string(),
                object : TypeToken<List<Plan>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getTrainers() : List<Instructor> {
        return try {
            val response = retrofit.getTrainers()
            Log.d(TAG, "TrainerURL: ${response.raw().request().url()}")
            Gson().fromJson<List<Instructor>>(
                response.body()?.string(),
                object : TypeToken<List<Instructor>>() {}.type
            )
        } catch (e: Exception) {
            emptyList()
        }
    }

}