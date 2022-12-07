package com.backend.controller;

import com.backend.model.Course;
import com.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController
{
    @Autowired
    CourseService courseService;

    @PostMapping("/plans/id/{plan_id}/courses/add")
    public ResponseEntity<Course> addExistingCourseToPlan(@PathVariable(value = "plan_id") int plan_id, @RequestBody Course courseRequest)
    {
        return new ResponseEntity<>(courseService.addCourseToPlan(plan_id,courseRequest), HttpStatus.CREATED);
    }

    @PostMapping("/instructors/id/{instructor_id}/courses/add")
    public ResponseEntity<Course> saveCourse(@PathVariable(value = "instructor_id") int instructor_id,
                                             @RequestBody Course courseRequest)
    {
        return new ResponseEntity<>(courseService.saveCourse(instructor_id,courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/courses/all")
    public ResponseEntity<List<Course>> getAllCourses()
    {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/plans/id/{plan_id}/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByPlanId(@PathVariable(value = "plan_id") int plan_id)
    {
        return new ResponseEntity<>(courseService.getAllCoursesByPlanId(plan_id), HttpStatus.OK);
    }

    @GetMapping("/instructors/id/{instructor_id}/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByInstructorId(@PathVariable(value = "instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(courseService.getAllCoursesByInstructorId(instructor_id),HttpStatus.OK);
    }

    @GetMapping("/courses/id/{id}")
    public ResponseEntity<Course> getCourseById( @PathVariable("id") int courseId)
    {
        return new ResponseEntity<>(courseService.getCourseById(courseId),HttpStatus.OK);
    }

    @PutMapping("courses/update/id/{id}")
    public ResponseEntity<Course> updateCourse( @PathVariable("id") int courseId, @RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.updateCourse(course,courseId),HttpStatus.OK);
    }

    @PutMapping ("courses/update/id/{course_id}/instructor/id/{instructor_id}")
    public ResponseEntity<Course> changeInstructorToCourse(@PathVariable("course_id") int course_id, @PathVariable("instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(courseService.changeInstructorToCourse(course_id,instructor_id),HttpStatus.OK);
    }

    @DeleteMapping("courses/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") int courseId)
    {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/plans/{plan_id}/courses/delete/id/{course_id}")
    public ResponseEntity<HttpStatus> deleteCourseFromPlan(@PathVariable(value = "plan_id") int plan_id, @PathVariable(value = "course_id") int course_id)
    {
        courseService.deleteCourseFromPlan(plan_id,course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}