package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Plan;
import com.backend.repository.CourseRepository;
import com.backend.repository.InstructorRepository;
import com.backend.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //add an extra json property to the course object to show the plans that the course is in
        List<Course> courses = courseRepository.findAll();
        courses.forEach(course -> {
                    ArrayList<Integer> plan_ids = new ArrayList<>();
                    //loop through all the plans and check if the course is in the plan
                    planRepository.findAll().forEach(plan ->
                            //if the course is in the plan, add the plan to the course
                            plan.getCourseSet().forEach(course1 ->
                                    {
                                        if (course1.getId() == course.getId()) {
                                            plan_ids.add(plan.getId());
                                        }
                                    }
                            )
                    );
                    course.setPlan_ids(plan_ids);
                    course.setInstructor_id(course.getInstructor().getId());
                }
        );
        return courses;
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
        courses.forEach(course -> {
                    ArrayList<Integer> plan_ids = new ArrayList<>();
                    //loop through all the plans and check if the course is in the plan
                    planRepository.findAll().forEach(plan ->
                            //if the course is in the plan, add the plan to the course
                            plan.getCourseSet().forEach(course1 ->
                                    {
                                        if (course1.getId() == course.getId()) {
                                            plan_ids.add(plan.getId());
                                        }
                                    }
                            )
                    );
                    course.setPlan_ids(plan_ids);
                    course.setInstructor_id(course.getInstructor().getId());
                }
        );
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
        courses.forEach(course -> {
                    ArrayList<Integer> plan_ids = new ArrayList<>();
                    //loop through all the plans and check if the course is in the plan
                    planRepository.findAll().forEach(plan ->
                            //if the course is in the plan, add the plan to the course
                            plan.getCourseSet().forEach(course1 ->
                                    {
                                        if (course1.getId() == course.getId()) {
                                            plan_ids.add(plan.getId());
                                        }
                                    }
                            )
                    );
                    course.setPlan_ids(plan_ids);
                    course.setInstructor_id(course.getInstructor().getId());
                }
        );
        return courses;
    }

    @Override
    public Course addExistingCourseToPlan(int plan_id, int course_id)
    {
        Course course = courseRepository.findById(course_id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",course_id));
        Plan plan = planRepository.findById(plan_id).orElseThrow(() -> new ResourceNotFoundException("Plan","Id",plan_id));
        plan.addCourse(course);
        planRepository.save(plan);
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
        ArrayList<Integer> plan_ids = new ArrayList<>();
        //loop through all the plans and check if the course is in the plan
        planRepository.findAll().forEach(plan ->
                //if the course is in the plan, add the plan to the course
                plan.getCourseSet().forEach(course1 ->
                        {
                            if (course1.getId() == existingCourse.getId()) {
                                plan_ids.add(plan.getId());
                            }
                        }
                )
        );
        existingCourse.setPlan_ids(plan_ids);
        existingCourse.setInstructor_id(existingCourse.getInstructor().getId());
        return existingCourse;
    }

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

    @Override
    public void deleteCourseFromPlan(int planId, int course_id)
    {
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("Plan","Id",planId));
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",course_id));
        plan.removeCourse(course_id);
        planRepository.save(plan);
    }
}