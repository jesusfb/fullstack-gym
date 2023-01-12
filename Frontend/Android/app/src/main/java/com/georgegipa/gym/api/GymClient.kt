package com.georgegipa.gym.api

import android.util.Log
import com.georgegipa.gym.models.Course
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


    fun getCourses(onDone: (List<Course>) -> Unit) {

        val service = retrofit.getCourses()
        //show the url in logcat
        Log.d(TAG, "CourseURL: ${service.request().url()}")
        service.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Gson().fromJson<List<Course>>(
                    response.body()?.string(),
                    object : TypeToken<List<Course>>() {}.type
                ).let {
                    Log.d(TAG, "onResponse: $it")
                    onDone(it)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}