package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService
{
    CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository)
    {
        super();
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse(Course course)
    {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int id)
    {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent())
        {
            return course.get();
        }
        else
        {
            throw new ResourceNotFoundException("Course","Id",id);
        }
    }

    @Override
    public Course updateCourse(Course course, int id)
    {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        existingCourse.setCourse_name(course.getCourse_name());
        existingCourse.setCourse_description(course.getCourse_description());
        courseRepository.save(existingCourse);
        return existingCourse;
    }

    @Override
    public void deleteCourse(int id)
    {
        courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        courseRepository.deleteById(id);
    }
}