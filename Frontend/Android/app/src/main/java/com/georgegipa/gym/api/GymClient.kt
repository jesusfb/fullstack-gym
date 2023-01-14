package com.georgegipa.gym.api

import android.util.Log
import com.georgegipa.gym.models.Course
import com.georgegipa.gym.models.Instructor
import com.georgegipa.gym.models.Plan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun checkAPI(onDone: (Boolean) -> Unit) {
        //hit the base url to check if the server is up and running
        //if response is 200, then the server is up and running
        retrofit.getCourses().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d(TAG, "onResponse: ${response.code()}")
                onDone(response.code() == 200)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                onDone(false)
            }
        })
    }


    fun getCourses(onDone: (List<Course>) -> Unit) {

        val service = retrofit.getCourses()
        Log.d(TAG, "CourseURL: ${service.request().url()}")
        service.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Gson().fromJson<List<Course>>(
                    response.body()?.string(),
                    object : TypeToken<List<Course>>() {}.type
                ).let {
                    Log.d(TAG, "onCourseResponse: $it")
                    onDone(it)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getPlans(onDone: (List<Plan>) -> Unit) {

        val service = retrofit.getPlans()
        Log.d(TAG, "PlanURL: ${service.request().url()}")
        service.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Gson().fromJson<List<Plan>>(
                    response.body()?.string(),
                    object : TypeToken<List<Plan>>() {}.type
                ).let {
                    Log.d(TAG, "onPlanResponse: $it")
                    onDone(it)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getInstructors(onDone: (List<Instructor>) -> Unit) {

        val service = retrofit.getTrainers()
        Log.d(TAG, "InstructorURL: ${service.request().url()}")
        service.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Gson().fromJson<List<Instructor>>(
                    response.body()?.string(),
                    object : TypeToken<List<Instructor>>() {}.type
                ).let {
                    Log.d(TAG, "onInstructorResponse: $it")
                    onDone(it)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

}