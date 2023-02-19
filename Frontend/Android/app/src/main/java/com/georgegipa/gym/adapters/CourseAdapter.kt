package com.georgegipa.gym.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.api.GymClient
import com.georgegipa.gym.models.Course
import com.georgegipa.gym.ui.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseAdapter(private val activity: Activity, private var courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_course, parent, false)
        return CourseViewHolder(v)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseImage: ImageView = itemView.findViewById(R.id.course_iv)
        private val nameTv: MaterialTextView by lazy { itemView.findViewById(R.id.course_tv) }
        private val descTv: MaterialTextView by lazy { itemView.findViewById(R.id.course_description_tv) }
        private val instructorIv: ImageView by lazy { itemView.findViewById(R.id.instructor_iv) }
        private val instructorMtv: MaterialTextView by lazy { itemView.findViewById(R.id.instructor_tv) }
        private val registerBtn: Button by lazy { itemView.findViewById(R.id.register_btn) }
        private var isRegistered = false

        fun bind(item: Course) {
            val context = activity as Context
            Glide.with(context).load(item.image).into(courseImage)
            nameTv.text = item.name
            descTv.text = item.description
            val instructor = ApiResponses.instructors.find { it.id == item.instructorId } ?: return
            Glide.with(context).load(instructor.image).into(instructorIv)
            val instructedStr = "Instructed by ${instructor.name} ${instructor.surname}"
            instructorMtv.text = instructedStr

            registerBtn.isEnabled = if (!item.plans.contains(ApiResponses.user.plan)) {
                registerBtn.text = "Not available for your plan"
                false
            } else {
                //course is available for the user's plan
                updateButton()
                true
            }

            registerBtn.setOnClickListener {
                //create a single selection dialog if the user is not registered to the course
                //if the user is registered to the course , unregister him
                val scheduledCourses = ApiResponses.getEventsForCourse(item.id)
                val scheduledCoursesNames = scheduledCourses.map { it.getReadAbleDate() }

                if (isRegistered) {
                    //user is already registered to a course , unregister him
                    val registeredCourse =
                        ApiResponses.getEventForCourse(item.id) ?: return@setOnClickListener

                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            (activity as MainActivity).snackMessage("Removing from ${item.name}...")
                        }
                        GymClient().unregisterFromCourse(
                            ApiResponses.user.id,
                            item.id,
                            registeredCourse.start,
                            registeredCourse.end
                        )
                        ApiResponses.refreshRegisteredCourses(ApiResponses.user.id)
                        withContext(Dispatchers.Main) {
                            (activity as MainActivity).snackMessage("You have been removed from ${item.name}")
                            updateButton()
                        }
                    }

                } else {
                    //create the above using radio buttons
                    var selectedItem = 0 //store the selected item index
                    MaterialAlertDialogBuilder(context)
                        .setTitle("Choose a date")
                        .setSingleChoiceItems(scheduledCoursesNames.toTypedArray(), 0) { _, which ->
                            selectedItem = which // store the selected item index
                        }
                        .setPositiveButton("Register") { dialog, _ ->
                            //register to the selected course
                            val selectedCourseTime = scheduledCourses[selectedItem]
                            GlobalScope.launch {
                                withContext(Dispatchers.Main) {
                                    (activity as MainActivity).snackMessage("Registering to ${item.name}...")
                                }

                                GymClient().registerToCourse(
                                    ApiResponses.user.id,
                                    item.id,
                                    selectedCourseTime.start,
                                    selectedCourseTime.end
                                )
                                ApiResponses.refreshRegisteredCourses(ApiResponses.user.id)
                                withContext(Dispatchers.Main) {
                                    (activity as MainActivity).snackMessage("You have been registered to ${item.name}")
                                    updateButton()
                                }
                            }
                            dialog.dismiss()
                        }
                        .setNegativeButton("Cancel") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }

        private fun updateButton() {
            val item = courseList[adapterPosition]
            val courseAlreadyRegistered = ApiResponses.checkIfUserIsRegisteredToCourse(item.id)
            if (courseAlreadyRegistered) {
                registerBtn.text = "Unregister"
            } else {
                registerBtn.text = "Register"
            }
            //return courseAlreadyRegistered
            isRegistered = courseAlreadyRegistered
        }
    }
}