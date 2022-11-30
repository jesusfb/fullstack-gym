package com.backend.service;

import com.backend.model.Course;

import java.util.List;

public interface CourseService
{
    List<Course> getAllCoursesByInstructorId (int instructor_id);
    Course saveCourse(int instructor_id,Course courseRequest);
    Course getCourseById(int id);
    Course updateCourse(Course course,int id);
    void deleteCourse(int id);
}