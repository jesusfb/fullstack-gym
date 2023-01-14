package com.georgegipa.gym.api

import com.georgegipa.gym.models.Course
import com.georgegipa.gym.models.Instructor
import com.georgegipa.gym.models.Plan

object ApiResponses {

    var courses: List<Course> = emptyList()
        private set
    var plans: List<Plan> = emptyList()
        private set
    var instructors: List<Instructor> = emptyList()
        private set


    suspend fun init(): Boolean {
        //use the new client
        val client = GymClient()
        if (client.checkAPI()) {
            instructors = client.getTrainers()
            plans = client.getPlans()
            courses = client.getCourses()
            return true
        }
        return false
    }

}