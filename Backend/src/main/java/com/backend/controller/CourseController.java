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
@CrossOrigin(origins = "http://localhost:8080")
public class CourseController
{
    @Autowired
    CourseService courseService;

    @PostMapping("/plans/courses/add")
    public ResponseEntity<Course> addExistingCourseToPlan(@RequestParam(value = "plan_id") int plan_id, @RequestParam(value = "course_id") int course_id)
    {
        return new ResponseEntity<>(courseService.addExistingCourseToPlan(plan_id,course_id), HttpStatus.OK);
    }

    @PostMapping("/instructors/courses/add")
    public ResponseEntity<Course> saveCourse(@RequestParam(value = "instructor_id") int instructor_id,
                                             @RequestBody Course courseRequest)
    {
        return new ResponseEntity<>(courseService.saveCourse(instructor_id,courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/courses/all")
    public ResponseEntity<List<Course>> getAllCourses()
    {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/plans/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByPlanId(@RequestParam(value = "plan_id") int plan_id)
    {
        return new ResponseEntity<>(courseService.getAllCoursesByPlanId(plan_id), HttpStatus.OK);
    }

    @GetMapping("/instructors/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByInstructorId(@RequestParam(value = "instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(courseService.getAllCoursesByInstructorId(instructor_id),HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<Course> getCourseById( @RequestParam("course_id") int course_id)
    {
        return new ResponseEntity<>(courseService.getCourseById(course_id),HttpStatus.OK);
    }

    @PutMapping("courses/update")
    public ResponseEntity<Course> updateCourse( @RequestParam("course_id") int course_id, @RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.updateCourse(course,course_id),HttpStatus.OK);
    }

    @PutMapping ("courses/update/instructor")
    public ResponseEntity<Course> changeInstructorToCourse(@RequestParam("course_id") int course_id, @RequestParam("instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(courseService.changeInstructorToCourse(course_id,instructor_id),HttpStatus.OK);
    }

    @DeleteMapping("courses/delete")
    public ResponseEntity<HttpStatus> deleteCourseById(@RequestParam("course_id") int course_id)
    {
        courseService.deleteCourse(course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/plans//delete/courses")
    public ResponseEntity<HttpStatus> deleteCourseFromPlan(@RequestParam(value = "plan_id") int plan_id, @RequestParam(value = "course_id") int course_id)
    {
        courseService.deleteCourseFromPlan(plan_id,course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}