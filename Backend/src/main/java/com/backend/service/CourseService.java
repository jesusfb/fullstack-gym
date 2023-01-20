package com.backend.service;

import com.backend.model.Course;
import java.util.List;

public interface CourseService
{
    List<Course> getAllCourses();
    Course changeInstructorToCourse(int course_id,int instructor_id);
    List<Course> getAllCoursesByInstructorId (int instructor_id);
    List<Course> getAllCoursesByPlanId(int plan_id);
    Course addExistingCourseToPlan(int plan_id, int course_id);
    Course saveCourse(int instructor_id,Course courseRequest);
    Course getCourseById(int id);
    Course updateCourse(Course course,int id);
    void deleteCourse(int id);
    void deleteCourseFromPlan(int planId,int course_id);
}