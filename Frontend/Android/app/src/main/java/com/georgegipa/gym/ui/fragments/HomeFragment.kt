package com.georgegipa.gym.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.georgegipa.gym.R
import com.georgegipa.gym.adapters.CourseAdapter
import com.georgegipa.gym.api.ApiResponses
import com.georgegipa.gym.databinding.FragmentHomeBinding
import com.georgegipa.gym.utils.getGreeting

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
        val recyclerView = binding.courseDetailsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.adapter = CourseAdapter(context, courseList)

        val greeting = getGreeting() + ", " + ApiResponses.user.name
        binding.greetingTv.text = greeting

        val courseList = ""
        if(courseList.isEmpty()){
            binding.courseDetailsRv.visibility = View.GONE
            binding.upcomingCoursesTv.text = requireContext().getString(R.string.no_courses_registered)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}