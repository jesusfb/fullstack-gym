package com.georgegipa.gym.api

import com.georgegipa.gym.models.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

object ApiResponses {

    var events: List<Event> = listOf()
        private set
    lateinit var courses: List<Course>
        private set
    lateinit var plans: List<Plan>
        private set
    lateinit var instructors: List<Instructor>
        private set
    lateinit var user: User
        private set

    suspend fun init(userId : Int): Boolean {
        //use the new client
        val client = GymClient()
        if (client.checkAPI()) {
            //request all the data in parallel
            CoroutineScope(Dispatchers.IO).launch {
                launch {
                    instructors = client.getTrainers()
                }
                launch {
                    plans = client.getPlans()
                }
                launch {
                    courses = client.getCourses()
                }
                launch {
                    user = client.getUser(userId)
                }
                launch {
                    events = client.getEvents()
                }
            }.join()
            return true
        }
        return false
    }

}