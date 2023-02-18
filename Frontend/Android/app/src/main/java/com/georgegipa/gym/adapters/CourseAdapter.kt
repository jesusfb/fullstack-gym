package com.georgegipa.gym.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.api.GymClient
import com.georgegipa.gym.models.Course
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CourseAdapter(private val context: Context, private var courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_course2, parent, false)
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

        fun bind(item: Course) {
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
                registerBtn.text = "Register"
                true
            }
            registerBtn.setOnClickListener {
                //create a multiple selection dialog
                val scheduledCourses =
                    ApiResponses.events.filter { it.courseId == item.id }.sortedBy { it.start }
                val scheduledCoursesNames = scheduledCourses.map { it.getReadAbleDate() }

                MaterialAlertDialogBuilder(context)
                    .setTitle("Choose a date")
                    .setItems(scheduledCoursesNames.toTypedArray()) { dialog, which ->
                        // Respond to item chosen
                        Toast.makeText(
                            context,
                            "You have registered for ${item.name} on ${scheduledCoursesNames[which]}",
                            Toast.LENGTH_SHORT
                        ).show()
                        //get the selected event
                        val selectedEvent = scheduledCourses[which]
                        GlobalScope.launch {
                            //register the user to the event
                            GymClient().registerToCourse(ApiResponses.user.id,item.id,selectedEvent.start, selectedEvent.end)
                        }
                    }
                    .show()
            }
        }
    }
}