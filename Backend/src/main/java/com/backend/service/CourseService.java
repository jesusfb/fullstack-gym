package com.backend.service;

import com.backend.model.Course;

import java.util.List;

public interface CourseService
{
    Course saveCourse (Course course);
    List<Course> getAllCourses();
    Course getCourseById(int id);
    Course updateCourse(Course course,int id);
    void deleteCourse(int id);
}