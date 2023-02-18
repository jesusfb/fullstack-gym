package com.georgegipa.gym.api

import android.widget.Toast
import com.georgegipa.gym.models.*
import kotlinx.coroutines.*

object ApiResponses {

    var gymEvents: List<GymEvent> = listOf()
        private set
    lateinit var courses: List<Course>
        private set
    lateinit var plans: List<Plan>
        private set
    lateinit var instructors: List<Instructor>
        private set
    lateinit var user: User
        private set
    private lateinit var allEvents : List<GymEvent>

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
                    gymEvents = client.getEventsForUser(userId)
                }
                launch {
                    allEvents = client.getAllEvents()
                }
            }.join()
            return true
        }
        return false
    }

    suspend fun refreshRegisteredCourses(userId : Int) {
        gymEvents = GymClient().getEventsForUser(userId)
    }

    fun getEventsForCourse(courseId : Int) : List<GymEvent> {
        return allEvents.filter { it.courseId == courseId }.sortedBy { it.start }
    }

    fun checkIfUserIsRegisteredToCourse(courseId : Int) : Boolean {
        return gymEvents.any { it.courseId == courseId }
    }

    //if yes return the event
    fun getEventForCourse(courseId : Int) : GymEvent? {
        return gymEvents.find { it.courseId == courseId }
    }

}