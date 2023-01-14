package com.georgegipa.gym.api

import android.content.Context
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

    fun initialize(onDone: (Boolean) -> Unit) {
        val client = GymClient()
        client.checkAPI { response ->
            if (response) {
                client.getInstructors { it1 ->
                    instructors = it1
                    client.getPlans { it2 ->
                        plans = it2
                        client.getCourses { it3 ->
                            courses = it3
                            onDone(true)
                        }
                    }
                }
            } else {
                onDone(false)
            }
        }

    }


}