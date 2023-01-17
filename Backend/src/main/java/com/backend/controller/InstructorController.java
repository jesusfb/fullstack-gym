package com.backend.controller;

import com.backend.model.Instructor;
import com.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class InstructorController
{
    @Autowired
    InstructorService instructorService;

    @PostMapping("instructors/add")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
    }

    @GetMapping("instructors/all")
    public ResponseEntity<List<Instructor>> getAllInstructors()
    {
        return new ResponseEntity<>(instructorService.getAllInstructors(),HttpStatus.OK);
    }

    @GetMapping("instructors")
    public ResponseEntity<Instructor> getInstructorById( @RequestParam("instructor_id") int instructor_id)
    {
        return new ResponseEntity<>(instructorService.getInstructorById(instructor_id),HttpStatus.OK);
    }

    @PutMapping("instructors/update")
    public ResponseEntity<Instructor> updateInstructor( @RequestParam("instructor_id") int instructor_id, @RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.updateInstructor(instructor,instructor_id),HttpStatus.OK);
    }

    @DeleteMapping("instructors/delete")
    public ResponseEntity<HttpStatus> deleteInstructorById(@RequestParam("instructor_id") int instructor_id)
    {
        instructorService.deleteInstructor(instructor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}