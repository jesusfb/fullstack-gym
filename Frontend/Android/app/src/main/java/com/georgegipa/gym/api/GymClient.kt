package com.georgegipa.gym.api

import android.util.Log
import com.georgegipa.gym.models.Course
import com.georgegipa.gym.models.pseudoCourses
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

    suspend fun getCourses(): List<pseudoCourses> {
        val response = retrofit.getCourses()
        if (response.isSuccessful) {
            Log.d(TAG, "Successful Request: ${response.raw().request().url()}")
            val data = response.body()
            val courseType = object : TypeToken<ArrayList<pseudoCourses>>() {}.type
            return Gson().fromJson<ArrayList<pseudoCourses>>(data.toString(), courseType)
        }
        return emptyList()
    }

}