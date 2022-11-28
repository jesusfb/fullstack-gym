package com.backend.controller;

import com.backend.model.Course;
import com.backend.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController
{
    private final CourseService courseService;

    public CourseController(CourseService courseService)
    {
        super();
        this.courseService = courseService;
    }

    // http://localhost:8080/courses/add  POST METHOD + JSON BODY
    @PostMapping("/add")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    // http://localhost:8080/courses/all GET METHOD
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses()
    {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    // http://localhost:8080/courses/id/1 GET METHOD
    @GetMapping("/id/{id}")
    public ResponseEntity<Course> getCourseById( @PathVariable("id") int courseId)
    {
        return new ResponseEntity<>(courseService.getCourseById(courseId),HttpStatus.OK);
    }

    // http://localhost:8080/courses/update/id/1 PUT METHOD + JSON BODY
    @PutMapping("/update/id/{id}")
    public ResponseEntity<Course> updateCourse( @PathVariable("id") int courseId, @RequestBody Course course)
    {
        return new ResponseEntity<>(courseService.updateCourse(course,courseId),HttpStatus.OK);
    }

    // http://localhost:8080/courses/delete/id/1 DELETE METHOD
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable("id") int courseId)
    {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}