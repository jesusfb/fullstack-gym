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
import com.georgegipa.gym.api.GymClient
import com.georgegipa.gym.databinding.FragmentCoursesBinding

class CoursesFragment : Fragment(R.layout.fragment_courses) {
    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.coursesRecyclerView.adapter = CourseAdapter(requireContext(), ApiResponses.courses)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}