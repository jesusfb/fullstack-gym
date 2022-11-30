package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.repository.CourseRepository;
import com.backend.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService
{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Course> getAllCoursesByInstructorId(int instructor_id)
    {
        if (!instructorRepository.existsById(instructor_id))
        {
            throw new ResourceNotFoundException("Instructor","Id",instructor_id);
        }

        List<Course> courses = courseRepository.findByInstructor_Id(instructor_id);
        return courses;
    }

    @Override
    public Course saveCourse(int instructor_id, Course courseRequest)
    {
        Course course = instructorRepository.findById(instructor_id).map( instructor ->
                {
                    courseRequest.setInstructor(instructor);
                    return courseRepository.save(courseRequest);
                }
        ).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",instructor_id));
        return course;
    }

    @Override
    public Course getCourseById(int id)
    {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        return existingCourse;
    }

    public Course updateCourse(Course course, int id)
    {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        existingCourse.setCourse_name(course.getCourse_name());
        existingCourse.setCourse_description(course.getCourse_description());
        courseRepository.save(existingCourse);
        return existingCourse;
        // αλλαγή instructor????
    }

    @Override
    public void deleteCourse(int id)
    {
        courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        courseRepository.deleteById(id);
    }
}