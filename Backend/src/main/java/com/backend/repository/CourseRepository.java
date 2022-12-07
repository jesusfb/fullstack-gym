package com.backend.repository;

import com.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  CourseRepository extends JpaRepository<Course,Integer>
{
    List<Course> findByInstructor_Id(int id);
    List<Course> findAllByPlanSet_Id(int plan_id);
}