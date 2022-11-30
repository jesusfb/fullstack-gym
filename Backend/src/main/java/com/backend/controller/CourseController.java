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

    @GetMapping("/instructors/{instructor_id}/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByInstructorId(@PathVariable(value = "instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(courseService.getAllCoursesByInstructorId(instructor_id),HttpStatus.OK);
    }

    @PostMapping("/instructors/{instructor_id}/courses/add")
    public ResponseEntity<Course> saveCourse(@PathVariable(value = "instructor_id") int instructor_id,
                                                 @RequestBody Course courseRequest)
    {
        return new ResponseEntity<>(courseService.saveCourse(instructor_id,courseRequest), HttpStatus.CREATED);
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

    @DeleteMapping("courses/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") int courseId)
    {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}