package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Plan;
import com.backend.model.User;
import com.backend.repository.CourseRepository;
import com.backend.repository.InstructorRepository;
import com.backend.repository.PlanRepository;
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

    @Autowired
    PlanRepository planRepository;

    @Override
    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    @Override
    public Course changeInstructorToCourse(int course_id, int instructor_id)
    {
        Course course = instructorRepository.findById(instructor_id).map(
                instructor -> {
                    Course existingCourse = courseRepository.findById(course_id).orElseThrow(()-> new ResourceNotFoundException("Course","Id",course_id));
                    existingCourse.setInstructor(instructor);
                    return courseRepository.save(existingCourse);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Instructor","Id",instructor_id));
        return course;
    }

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
    public List<Course> getAllCoursesByPlanId(int plan_id)
    {
        if (!planRepository.existsById(plan_id))
        {
            throw new ResourceNotFoundException("Plan","Id",plan_id);
        }
        List<Course> courses = courseRepository.findAllByPlanSet_Id(plan_id);
        return courses;
    }

    @Override
    public Course addCourseToPlan(int plan_id, Course courseRequest)
    {
        Course course = planRepository.findById(plan_id).map(plan -> {
            int courseId = courseRequest.getCourse_id();

            if (courseId != 0L) {
                Course existingCourse = courseRepository.findById(courseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Course","Id",courseId));
                plan.addCourse(existingCourse);
                planRepository.save(plan);
                return existingCourse;
            }

            plan.addCourse(courseRequest);
            return courseRepository.save(courseRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Plan","Id",plan_id));
        return course;
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
        existingCourse.setCourse_date(course.getCourse_date());
        courseRepository.save(existingCourse);
        return existingCourse;
    }

    @Override
    public void deleteCourse(int id)
    {
        courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteCourseFromPlan(int planId, int course_id)
    {
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("Plan","Id",planId));
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",course_id));
        plan.removeCourse(course_id);
        planRepository.save(plan);
    }
}