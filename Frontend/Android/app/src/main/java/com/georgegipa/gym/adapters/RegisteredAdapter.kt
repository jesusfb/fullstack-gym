package com.georgegipa.gym.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.georgegipa.gym.R
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.models.GymEvent
import com.google.android.material.textview.MaterialTextView

class RegisteredAdapter(
    private val items: List<GymEvent>
) : RecyclerView.Adapter<RegisteredAdapter.RegisteredAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredAdapterViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_course_info,
            parent, false
        )
        return RegisteredAdapterViewHolder(v)
    }

    override fun onBindViewHolder(holder: RegisteredAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RegisteredAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeTv by lazy { itemView.findViewById<TextView>(R.id.time_tv) }
        private val courseNameTv by lazy { itemView.findViewById<TextView>(R.id.course_tv) }
        private val instructorTv by lazy { itemView.findViewById<MaterialTextView>(R.id.trainer_tv) }

        fun bind(item: GymEvent) {
            ApiResponses.courses.find { it.id == item.courseId }?.let { course ->
                timeTv.text = item.getStartingTime()
                courseNameTv.text = course.name
                ApiResponses.instructors.find { it.id == course.instructorId }?.let { instructor ->
                    instructorTv.text = instructor.name + " " + instructor.surname
                }
            }
        }
    }
}