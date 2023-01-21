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
import com.georgegipa.gym.models.Course
import com.google.android.material.textview.MaterialTextView

class CourseAdapter (private val context: Context, private var courseList: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_course, parent, false)
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
        private val courseImage: ImageView = itemView.findViewById(R.id.course_iv)
        private val timeTv : MaterialTextView by lazy { itemView.findViewById(R.id.time_tv) }
        private val nameTv : MaterialTextView by lazy { itemView.findViewById(R.id.course_tv) }
        private val descTv : MaterialTextView by lazy { itemView.findViewById(R.id.course_description_tv) }
        //private val trainerTv : MaterialTextView by lazy { itemView.findViewById(R.id.trainer_tv) }
        private val registerBtn : Button by lazy { itemView.findViewById(R.id.register_btn) }

        fun bind(item: Course) {
            Glide.with(context).load(item.image).into(courseImage)
            timeTv.text = "Date incoming"
            nameTv.text = item.name
            descTv.text = item.description
            if(!item.plans.contains(ApiResponses.user.plan))
            {
                registerBtn.isEnabled = false
                registerBtn.text = "Not available for your plan"
            }
            registerBtn.setOnClickListener {
                Toast.makeText(context, "You have registered for ${item.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}