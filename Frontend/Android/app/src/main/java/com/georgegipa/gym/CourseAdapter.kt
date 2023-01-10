package com.georgegipa.gym

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.georgegipa.gym.models.Course
import com.google.android.material.textview.MaterialTextView

class CourseAdapter (private val context: Context, private var courseList: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_course_info, parent, false)
        return CourseViewHolder(v)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    //update the list
    fun updateList(newList: List<Course>) {
        courseList = newList
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeTv : MaterialTextView by lazy { itemView.findViewById(R.id.time_tv) }
        private val nameTv : MaterialTextView by lazy { itemView.findViewById(R.id.course_tv) }
        private val trainerTv : MaterialTextView by lazy { itemView.findViewById(R.id.trainer_tv) }

        fun bind(item: Course) {
            timeTv.text = item.date
            nameTv.text = item.name
            trainerTv.text = item.trainer
        }
    }
}